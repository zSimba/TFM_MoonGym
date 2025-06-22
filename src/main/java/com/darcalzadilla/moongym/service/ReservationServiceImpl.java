package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.entity.Reservation;
import com.darcalzadilla.moongym.entity.User;
import com.darcalzadilla.moongym.repository.IClassSessionRepository;
import com.darcalzadilla.moongym.repository.IReservationRepository;
import com.darcalzadilla.moongym.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService{
    private final IReservationRepository reservationRepo;
    private final IClassSessionRepository sessionRepo;
    private final IUserRepository userRepo;

    public ReservationServiceImpl(IReservationRepository reservationRepo,
                                  IClassSessionRepository sessionRepo,
                                  IUserRepository userRepo) {
        this.reservationRepo = reservationRepo;
        this.sessionRepo     = sessionRepo;
        this.userRepo        = userRepo;
    }

    @Override
    @Transactional
    public Reservation bookSession(Long sessionId, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + username));
        ClassSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Clase no encontrada: " + sessionId));

        boolean already = reservationRepo
                .existsByUserAndClassSession(user, session);
        if (already) {
            throw new IllegalStateException("Ya has reservado esta sesión");
        }

        if (session.getAvailableSeats() <= 0) {
            throw new IllegalStateException("No hay plazas disponibles");
        }

        // 3) Crear la reserva
        Reservation res = new Reservation();
        res.setUser(user);
        res.setClassSession(session);
        session.getReservations().add(res);

        return reservationRepo.save(res);
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId, String username) {
        Reservation res = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada: " + reservationId));

        if (!res.getUser().getUsername().equals(username)) {
            throw new IllegalArgumentException("No tienes permiso para cancelar esta reserva");
        }
        // Eliminar de la sesión
        ClassSession session = res.getClassSession();
        session.getReservations().remove(res);

        reservationRepo.delete(res);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> listUserReservations(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + username));
        return reservationRepo.findByUser(user);
    }

    @Override
    public List<Reservation> findByUser(String name) {
        return reservationRepo.findAll();
    }

}
