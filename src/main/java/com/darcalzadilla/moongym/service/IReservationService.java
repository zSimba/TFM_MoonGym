package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.Reservation;

import java.util.List;

public interface IReservationService {
    /**
     * Reserva una plaza en una sesión para un usuario.
     * @param sessionId  ID de la clase a reservar.
     * @param username   Nombre de usuario que reserva.
     * @return la reserva creada.
     * @throws IllegalArgumentException si la sesión o el usuario no existen.
     * @throws IllegalStateException    si no hay plazas disponibles o ya está reservado.
     */
    Reservation bookSession(Long sessionId, String username);

    /**
     * Cancela una reserva existente.
     * @param reservationId ID de la reserva.
     * @param username      Usuario que solicita la cancelación.
     * @throws IllegalArgumentException si la reserva no existe o no pertenece al usuario.
     */
    void cancelReservation(Long reservationId, String username);

    /**
     * Lista las reservas de un usuario.
     */
    List<Reservation> listUserReservations(String username);

    List<Reservation> findByUser(String name);
}
