package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.service.ClassSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {
    private final ClassSessionService classSessionService;

    public DashboardController(ClassSessionService classSessionService) {
        this.classSessionService = classSessionService;
    }

    /**
     * Muestra el panel de usuario tras el login, con todas las clases disponibles.
     * @param model     Modelo para Thymeleaf
     * @param principal Información del usuario autenticado
     * @return "dashboard" (templates/dashboard.html)
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        // Nombre del usuario autenticado
        String username = principal.getName();
        model.addAttribute("username", username);

        // Lista de todas las clases disponibles
        List<ClassSession> classes = classSessionService.listAllSessions();
        model.addAttribute("classes", classes);

        return "dashboard";
    }
}
