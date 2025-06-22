package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.dto.ClassExampleDto;
import com.darcalzadilla.moongym.entity.ClassSession;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClassSessionService {
    List<ClassSession> findTop3();

    /**
     * Obtiene las sesiones programadas para hoy.
     */
    List<ClassSession> findTodaySessions();

    /**
     * Obtiene sesiones recomendadas para un usuario (lógica de negocio simple).
     */
    List<ClassSession> findRecommendedSessions(String username);

    /** Método CRUD adicional si lo necesitas */
    ClassSession getSessionById(Long id);

    List<ClassSession> listAllSessions();

    void createSession(ClassSession classSession);

    void updateSession(ClassSession classSession);

    void deleteSession(Long id);

    List<ClassExampleDto> listDistinctClassExamples();
    List<ClassSession> findByNameAndDateRange(
            String name,
            LocalDateTime from,
            LocalDateTime to
    );

    Optional<ClassSession> findById(Long sessionId);

    List<String> findDistinctClassNames();

    List<ClassSession> findSessionsNext7DaysByName(String name);
}
