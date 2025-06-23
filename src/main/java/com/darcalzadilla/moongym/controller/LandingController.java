package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.dto.ClassExampleDto;
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

    @GetMapping("/")
    public String landing(Model model) {
        List<ClassExampleDto> examples = classSessionService.listDistinctClassExamples();
        model.addAttribute("classExamples", examples);
        return "landing";
    }
}
