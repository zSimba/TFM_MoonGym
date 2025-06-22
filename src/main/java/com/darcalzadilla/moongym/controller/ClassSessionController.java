package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.dto.ClassTypeDto;
import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.service.ClassSessionService;
import com.darcalzadilla.moongym.service.ClassSessionWeeklyGenerator;
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
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clases")
public class ClassSessionController {
    private final ClassSessionService sessionService;
    private final ClassSessionWeeklyGenerator weeklyGenerator;

    public ClassSessionController(ClassSessionService sessionService,
                                  ClassSessionWeeklyGenerator weeklyGenerator) {
        this.sessionService = sessionService;
        this.weeklyGenerator = weeklyGenerator;
    }

    /**
     * Catálogo de modalidades: un solo cuadro por cada nombre de clase
     */
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

    /**
     * Horarios semanales de una modalidad concreta.
     * Evita el choque de rutas usando “/horarios”
     */
    @GetMapping("/{name}/horarios")
    public String twoWeeksSchedule(
            @PathVariable String name,
            Model model) {

        weeklyGenerator.generateTwoWeeksSessionsFor(name);

        LocalDate currentMonday = LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime start = currentMonday.atStartOfDay();
        LocalDateTime end   = currentMonday.plusWeeks(1)
                .plusDays(4).atTime(LocalTime.MAX);

        List<ClassSession> sessions = sessionService
                .findByNameAndDateRange(name, start, end);

        Map<DayOfWeek, List<ClassSession>> sessionsByDay = sessions.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getDateTime().getDayOfWeek(),
                        () -> new EnumMap<>(DayOfWeek.class),
                        Collectors.toList()
                ));

        model.addAttribute("className",      name);
        model.addAttribute("sessionsByDay",  sessionsByDay);
        // lista fija Mon–Fri en orden
        model.addAttribute("days", List.of(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY));

        return "schedules";
    }

    /** Formulario de reserva */
    @GetMapping("/book")
    public String bookForm(@RequestParam("sessionId") Long sessionId,
                           Model model) {
        ClassSession session = sessionService.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("Sesión no encontrada: " + sessionId));
        model.addAttribute("session", session);
        return "book_form";
    }

    /**
     * Inicializa un EnumMap con todas las claves MONDAY..SUNDAY apuntando a listas vacías
     */
    private Map<DayOfWeek, List<ClassSession>> initEmptyWeek() {
        Map<DayOfWeek, List<ClassSession>> map = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek d : DayOfWeek.values()) {
            map.put(d, new ArrayList<>());
        }
        return map;
    }
}