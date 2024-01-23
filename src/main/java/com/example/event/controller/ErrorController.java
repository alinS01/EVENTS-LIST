package com.example.event.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage");
        return "error";
    }

}
