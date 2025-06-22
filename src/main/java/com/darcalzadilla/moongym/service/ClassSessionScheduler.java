package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.repository.IClassSessionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class ClassSessionScheduler {
    private final IClassSessionRepository repo;

    public ClassSessionScheduler(IClassSessionRepository repo) {
        this.repo = repo;
    }

    @Scheduled(cron = "0 0 0 * * MON-FRI")
    public void generateTodayRecurringSessions() {
        LocalDate today = LocalDate.now();
        List<ClassSession> templates = repo.findByRecurringWeekdaysTrueAndDateTimeBefore(today.atStartOfDay());
        for (ClassSession tmpl : templates) {
            LocalTime time = tmpl.getDateTime().toLocalTime();
            LocalDateTime newDateTime = LocalDateTime.of(today, time);

            boolean exists = repo.existsByNameAndDateTime(tmpl.getName(), newDateTime);
            if (!exists) {
                ClassSession copy = new ClassSession();
                copy.setName(tmpl.getName());
                copy.setDescription(tmpl.getDescription());
                copy.setCapacity(tmpl.getCapacity());
                copy.setImageUrl(tmpl.getImageUrl());
                copy.setIntensityLevel(tmpl.getIntensityLevel());
                copy.setDateTime(newDateTime);
                copy.setRecurringWeekdays(true);
                repo.save(copy);
            }
        }
    }
}
