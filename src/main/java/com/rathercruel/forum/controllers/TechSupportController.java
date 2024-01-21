package com.rathercruel.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TechSupportController {

    @GetMapping("/tech-support")
    private String techSupport(Model model) {
        return "profile";
    }
}
