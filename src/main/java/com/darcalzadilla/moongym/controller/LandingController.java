package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.service.ClassSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LandingController {
    private final ClassSessionService classSessionService;

    public LandingController(ClassSessionService classSessionService) {
        this.classSessionService = classSessionService;
    }

    /**
     * Muestra la landing page pública antes de iniciar sesión.
     * @param model Modelo para Thymeleaf
     * @return nombre de la plantilla Thymeleaf (landing.html)
     */
    @GetMapping("/")
    public String showLandingPage(Model model) {
        // Obtiene tres ejemplos de clases para mostrar en la landing
        List<ClassSession> classExamples = classSessionService.findTop3();
        model.addAttribute("classExamples", classExamples);
        return "landing";
    }
}
