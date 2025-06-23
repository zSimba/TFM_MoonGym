package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.repository.IClassSessionRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ClassSessionWeeklyGenerator {

    private final IClassSessionRepository repo;
    public ClassSessionWeeklyGenerator(IClassSessionRepository repo) {
        this.repo = repo;
    }

    private static final List<DayOfWeek> WEEK_DAYS = List.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY
    );

    public void generateTwoWeeksSessionsFor(String className) {
        List<ClassSession> templates = repo.findByName(className);
        if (templates.isEmpty()) return;

        LocalDate currentMonday = findCurrentMonday();
        List<LocalDate> baseMondays = List.of(currentMonday, currentMonday.plusWeeks(1));
        LocalDateTime now = LocalDateTime.now();

        baseMondays.forEach(baseMonday ->
                generateWeekSessionsFor(baseMonday, templates, className, now)
        );
    }

    private LocalDate findCurrentMonday() {
        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    private void generateWeekSessionsFor(
            LocalDate baseMonday,
            List<ClassSession> templates,
            String className,
            LocalDateTime now
    ) {
        for (ClassSession template : templates) {
            LocalTime templateTime = template.getDateTime().toLocalTime();
            for (DayOfWeek day : WEEK_DAYS) {
                LocalDateTime sessionDateTime = buildSessionDateTime(baseMonday, day, templateTime);
                if (shouldCreateSession(className, sessionDateTime, now)) {
                    saveSessionFromTemplate(template, sessionDateTime);
                }
            }
        }
    }

    private LocalDateTime buildSessionDateTime(
            LocalDate baseMonday,
            DayOfWeek day,
            LocalTime time
    ) {
        LocalDate date = baseMonday.with(TemporalAdjusters.nextOrSame(day));
        return LocalDateTime.of(date, time);
    }

    private boolean shouldCreateSession(
            String className,
            LocalDateTime sessionDateTime,
            LocalDateTime now
    ) {
        return sessionDateTime.isAfter(now)
                && !repo.existsByNameAndDateTime(className, sessionDateTime);
    }

    private void saveSessionFromTemplate(
            ClassSession template,
            LocalDateTime sessionDateTime
    ) {
        ClassSession copy = new ClassSession();
        copy.setName(template.getName());
        copy.setDescription(template.getDescription());
        copy.setCapacity(template.getCapacity());
        copy.setImageUrl(template.getImageUrl());
        copy.setIntensityLevel(template.getIntensityLevel());
        copy.setDateTime(sessionDateTime);
        repo.save(copy);
    }
}
