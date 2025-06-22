package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.dto.ClassTypeDto;
import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.service.ClassSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clases")
public class ClassSessionController {
    private final ClassSessionService sessionService;

    public ClassSessionController(ClassSessionService sessionService) {
        this.sessionService = sessionService;
    }

    /** Catálogo de modalidades (un único cuadro por nombre de clase). */
    @GetMapping
    public String catalog(Model model) {
        List<ClassSession> all = sessionService.listAllSessions();
        List<ClassTypeDto> types = all.stream()
                .collect(Collectors.groupingBy(ClassSession::getName))
                .entrySet().stream()
                .map(e -> {
                    ClassSession first = e.getValue().get(0);
                    return new ClassTypeDto(
                            e.getKey(),
                            first.getDescription(),
                            first.getImageUrl(),
                            first.getIntensityLevel()
                    );
                })
                .toList();
        model.addAttribute("classTypes", types);
        return "catalog";
    }

    /** Horarios semanales de una modalidad. */
    @GetMapping("/{name}")
    public String schedules(
            @PathVariable String name,
            Model model
    ) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime from = startOfWeek.atStartOfDay();
        LocalDateTime to   = startOfWeek.plusDays(6).atTime(LocalTime.MAX);

        List<ClassSession> week = sessionService.findByNameAndDateRange(name, from, to);

        // Mapa DayOfWeek → sesiones de ese día, en orden de inserción
        Map<DayOfWeek, List<ClassSession>> byDay = week.stream()
                .collect(Collectors.groupingBy(
                        sess -> sess.getDateTime().getDayOfWeek(),
                        () -> new EnumMap<>(DayOfWeek.class),
                        Collectors.toList()
                ));

        // Aseguramos que existan todas las claves Mon–Sun
        List<DayOfWeek> days = List.of(
                DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY
        );
        model.addAttribute("days", days);
        model.addAttribute("sessionsByDay", byDay);
        model.addAttribute("className", name);
        return "schedules";
    }

    /** Formulario de reserva */
    @GetMapping("/book")
    public String bookForm(
            @RequestParam("sessionId") Long sessionId,
            Model model
    ) {
        Optional<ClassSession> s = sessionService.findById(sessionId);
        model.addAttribute("session", s);
        return "book_form";
    }
}
