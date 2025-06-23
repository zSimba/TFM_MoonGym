package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.entity.Reservation;
import com.darcalzadilla.moongym.service.ClassSessionService;
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

    @GetMapping("/new")
    public String showBookForm(@RequestParam("classId") Long classId,
                               Model model) {
        ClassSession cs = sessionService.findById(classId)
                .orElseThrow(() -> new NoSuchElementException("Sesión no encontrada: " + classId));
        model.addAttribute("classSession", cs);
        return "book_form";
    }
    @PostMapping("/book")
    public String bookSession(@RequestParam("sessionId") Long sessionId,
                              Principal principal,
                              Model model) {
        Reservation reservation = reservationService.bookSession(sessionId, principal.getName());
        model.addAttribute("reservation", reservation);
        return "reservation_success";
    }

    @GetMapping
    public String listMyReservations(Model model, Principal principal) {
        List<Reservation> reservations = reservationService.findByUser(principal.getName());
        model.addAttribute("reservations", reservations);
        return "my_reservations";
    }

    @PostMapping("/{id}/cancel")
    public String cancelReservation(@PathVariable("id") Long reservationId,
                                    Principal principal) {
        reservationService.cancelReservation(reservationId, principal.getName());
        return "redirect:/reservas";
    }
}