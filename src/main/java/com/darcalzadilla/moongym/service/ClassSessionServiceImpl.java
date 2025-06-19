package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.repository.IClassSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
}
