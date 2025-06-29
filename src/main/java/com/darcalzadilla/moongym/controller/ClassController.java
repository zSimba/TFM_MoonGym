package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.service.ClassSessionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/classes")
@PreAuthorize("hasRole('ADMIN')")
public class ClassController {
    private final ClassSessionService classSessionService;

    public ClassController(ClassSessionService classSessionService) {
        this.classSessionService = classSessionService;
    }


    @GetMapping
    public String listClasses(Model model) {
        model.addAttribute("sessions", classSessionService.listAllSessions());
        return "admin/class_list";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("classSession", new ClassSession());
        return "admin/class_form";
    }


    @PostMapping
    public String createClass(@ModelAttribute("classSession") ClassSession classSession) {
        classSessionService.createSession(classSession);
        return "redirect:/admin/classes";
    }


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ClassSession session = classSessionService.getSessionById(id);
        model.addAttribute("classSession", session);
        return "admin/class_form";
    }


    @PostMapping("/{id}")
    public String updateClass(@PathVariable Long id,
                              @ModelAttribute("classSession") ClassSession classSession) {
        classSession.setId(id);
        classSessionService.updateSession(classSession);
        return "redirect:/admin/classes";
    }


    @PostMapping("/{id}/delete")
    public String deleteClass(@PathVariable Long id) {
        classSessionService.deleteSession(id);
        return "redirect:/admin/classes";
    }
}
