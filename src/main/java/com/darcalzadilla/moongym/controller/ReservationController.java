package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.entity.Reservation;
import com.darcalzadilla.moongym.service.ClassSessionService;
import com.darcalzadilla.moongym.service.IReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reservas")
public class ReservationController {

    private final ClassSessionService classSessionService;
    private final IReservationService reservationService;

    public ReservationController(ClassSessionService classSessionService,
                                 IReservationService reservationService) {
        this.classSessionService = classSessionService;
        this.reservationService = reservationService;
    }

    /**
     * 1) Pantalla inicial: muestro los tipos de clase (sin duplicados por día)
     */
    @GetMapping("/clases")
    public String listClassTypes(Model model) {
        // Supongamos que este método devuelve los nombres únicos de las clases
        List<String> classNames = classSessionService.findDistinctClassNames();
        model.addAttribute("classNames", classNames);
        return "session/class_types";
    }

    /**
     * 2) Tras elegir un tipo de clase, muestro la semana entrante
     *    agrupada por día, y dejo seleccionar los días (checkbox)
     */
    @GetMapping("/clases/{name}/semana")
    public String showWeeklySchedule(@PathVariable("name") String name, Model model) {
        // Obtengo todas las sesiones de los próximos 7 días de esa clase
        List<ClassSession> week = classSessionService.findSessionsNext7DaysByName(name);

        // Agrupo por día de la semana, usando EnumMap para garantizar orden LUN→DOM
        Map<DayOfWeek, List<ClassSession>> sessionsByDay = week.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getDateTime().getDayOfWeek(),
                        () -> new EnumMap<>(DayOfWeek.class),
                        Collectors.toList()
                ));

        model.addAttribute("sessionsByDay", sessionsByDay);
        model.addAttribute("className", name);
        return "session/weekly_schedule";
    }

    /**
     * 3) Procesar reserva — puede venir uno o varios sessionIds
     */
    @PostMapping("/reservas/book")
    public String bookSessions(@RequestParam("sessionIds") List<Long> sessionIds,
                               Principal principal,
                               Model model) {
        List<Reservation> reservations = sessionIds.stream()
                .map(id -> reservationService.bookSession(id, principal.getName()))
                .collect(Collectors.toList());
        model.addAttribute("reservations", reservations);
        return "session/reservation_success";
    }

    /**
     * 4) Listar las reservas del usuario
     */
    @GetMapping("/reservas")
    public String listMyReservations(Model model, Principal principal) {
        List<Reservation> reservations = reservationService.findByUser(principal.getName());
        model.addAttribute("reservations", reservations);
        return "session/my_reservations";
    }

    /**
     * 5) Cancelar una reserva concreta
     */
    @PostMapping("/reservas/{id}/cancel")
    public String cancelReservation(@PathVariable("id") Long reservationId,
                                    Principal principal) {
        reservationService.cancelReservation(reservationId, principal.getName());
        return "redirect:/reservas";
    }
}