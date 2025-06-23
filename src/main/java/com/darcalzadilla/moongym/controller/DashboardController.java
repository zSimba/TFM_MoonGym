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

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);

        List<ClassSession> classes = classSessionService.listAllSessions();
        model.addAttribute("classes", classes);

        return "dashboard";
    }
}
