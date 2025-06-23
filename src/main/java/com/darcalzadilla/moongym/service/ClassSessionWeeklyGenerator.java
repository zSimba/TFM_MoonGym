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

    public void generateTwoWeeksSessionsFor(String className) {
        LocalDate today = LocalDate.now();
        LocalDate currentMonday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate nextMonday    = currentMonday.plusWeeks(1);

        // 1) Obtén TODAS las plantillas recurrentes de esa clase
        List<ClassSession> templates = repo.findByName(className);

        // 2) Para cada semana (actual y siguiente)…
        for (LocalDate baseMonday : List.of(currentMonday, nextMonday)) {
            LocalDateTime now = LocalDateTime.now();

            for (ClassSession tmpl : templates) {
                LocalTime templateTime = tmpl.getDateTime().toLocalTime();

                // 3) Genera L–V
                for (DayOfWeek day : List.of(
                        DayOfWeek.MONDAY,
                        DayOfWeek.TUESDAY,
                        DayOfWeek.WEDNESDAY,
                        DayOfWeek.THURSDAY,
                        DayOfWeek.FRIDAY)) {

                    LocalDate sessionDate = baseMonday.with(TemporalAdjusters.nextOrSame(day));
                    LocalDateTime sessionDt = LocalDateTime.of(sessionDate, templateTime);

                    // 4) Sólo futuros y no duplicados
                    if (sessionDt.isAfter(now)
                            && !repo.existsByNameAndDateTime(className, sessionDt)) {

                        ClassSession copy = new ClassSession();
                        copy.setName(tmpl.getName());
                        copy.setDescription(tmpl.getDescription());
                        copy.setCapacity(tmpl.getCapacity());
                        copy.setImageUrl(tmpl.getImageUrl());
                        copy.setIntensityLevel(tmpl.getIntensityLevel());
                        copy.setDateTime(sessionDt);
                        copy.setRecurringWeekdays(true);
                        repo.save(copy);
                    }
                }
            }
        }
    }
}
