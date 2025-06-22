package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.dto.ClassExampleDto;
import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.repository.IClassSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {

    private final IClassSessionRepository repository;

    public ClassSessionServiceImpl(IClassSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClassSession> findTop3() {
        return repository.findTop3ByOrderByDateTimeAsc();
    }

    @Override
    public List<ClassSession> findTodaySessions() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(23, 59, 59);
        return repository.findByDateTimeBetween(start, end);
    }

    @Override
    public List<ClassSession> findRecommendedSessions(String username) {
        // Lógica de recomendación simple: mostrar próximas 3 sesiones
        return findTop3();
    }

    @Override
    public ClassSession getSessionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Clase no encontrada: " + id));
    }

    @Override
    public List<ClassSession> listAllSessions() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void createSession(ClassSession classSession) {
        repository.save(classSession);
    }

    @Override
    @Transactional
    public void updateSession(ClassSession classSession) {
        repository.save(classSession);
    }

    @Override
    @Transactional
    public void deleteSession(Long id) {
        repository.deleteById(id);
    }

    public List<ClassExampleDto> listDistinctClassExamples() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(ClassSession::getName))
                .values().stream()
                .map(sessList -> sessList.get(0))
                .map(s -> new ClassExampleDto(
                        s.getName(),
                        s.getDescription(),
                        s.getImageUrl(),
                        s.getIntensityLevel()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClassSession> findByNameAndDateRange(String name, LocalDateTime from, LocalDateTime to) {
        return repository.findByNameAndDateTimeBetween(name, from, to);
    }

    @Override
    public Optional<ClassSession> findById(Long sessionId) {
        return repository.findById(sessionId);
    }

    @Override
    public List<String> findDistinctClassNames() {
        return repository.findDistinctNames();
    }

    @Override
    public List<ClassSession> findSessionsNext7DaysByName(String name) {
        LocalDateTime now      = LocalDateTime.now();
        LocalDateTime in7Days  = now.plusDays(7);
        return repository.findByNameAndDateTimeBetweenOrderByDateTime(name, now, in7Days);
    }
}
