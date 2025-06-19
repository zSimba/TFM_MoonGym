package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.ClassSession;

import java.util.List;

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
}
