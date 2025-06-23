package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.entity.Reservation;
import com.darcalzadilla.moongym.service.ClassSessionService;
import com.darcalzadilla.moongym.service.ClassSessionServiceImpl;
import com.darcalzadilla.moongym.service.IReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/reservas")
public class ReservationController {

    private final IReservationService reservationService;
    private final ClassSessionService sessionService;

    public ReservationController(IReservationService reservationService, ClassSessionService sessionService) {
        this.reservationService = reservationService;
        this.sessionService = sessionService;
    }

    /**
     *  Procesar reserva
     */
    @GetMapping("/new")
    public String showBookForm(@RequestParam("classId") Long classId,
                               Model model) {
        ClassSession session = sessionService.findById(classId)
                .orElseThrow(() -> new NoSuchElementException("Sesión no encontrada: " + classId));
        model.addAttribute("session", session);
        return "book_form";
    }
    @PostMapping("/new")
    public String bookSession(@RequestParam("classId") Long classId,
                              Principal principal,
                              Model model) {
        Reservation reservation = reservationService
                .bookSession(classId, principal.getName());
        model.addAttribute("reservation", reservation);
        return "reservation_success";
    }

    /**
     * Listar las reservas del usuario
     */
    @GetMapping
    public String listMyReservations(Model model, Principal principal) {
        List<Reservation> reservations = reservationService.findByUser(principal.getName());
        model.addAttribute("reservations", reservations);
        return "my_reservations";
    }

    /**
     * Cancelar una reserva concreta
     */
    @PostMapping("/reservas/{id}/cancel")
    public String cancelReservation(@PathVariable("id") Long reservationId,
                                    Principal principal) {
        reservationService.cancelReservation(reservationId, principal.getName());
        return "redirect:/reservas";
    }
}