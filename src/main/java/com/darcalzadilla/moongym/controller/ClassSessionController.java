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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

    @GetMapping("/{name}/horarios")
    public String twoWeeksSchedule(
            @PathVariable String name,
            Model model) {

        weeklyGenerator.generateTwoWeeksSessionsFor(name);

        LocalDate today = LocalDate.now();
        LocalDate friday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end   = friday.atTime(LocalTime.MAX);

        List<ClassSession> all = sessionService.findByNameAndDateRange(name, start, end);

        Map<LocalDate, List<ClassSession>> sessionsByDate = new TreeMap<>();
        for (LocalDate d = today; !d.isAfter(friday); d = d.plusDays(1)) {
            sessionsByDate.put(d, new ArrayList<>());
        }
        all.forEach(s -> {
            LocalDate d = s.getDateTime().toLocalDate();
            sessionsByDate.get(d).add(s);
        });

        model.addAttribute("className",     name);
        model.addAttribute("sessionsByDate", sessionsByDate);
        model.addAttribute("dates",          sessionsByDate.keySet());
        return "schedules";
    }
}