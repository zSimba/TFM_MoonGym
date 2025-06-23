package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.Reservation;

import java.util.List;

public interface IReservationService {

    Reservation bookSession(Long sessionId, String username);
    void cancelReservation(Long reservationId, String username);
    List<Reservation> findByUser(String name);
}
