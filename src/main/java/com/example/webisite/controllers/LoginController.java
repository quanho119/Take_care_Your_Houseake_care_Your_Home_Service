package com.example.webisite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String showLoginPage() {
        return "login";
    }
    @PostMapping
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        if ("admin".equals(username) && "12345".equals(password)) {
            return "redirect:/home";
        }
        model.addAttribute("errorMessage", "Invalid username or password.");
        return "login";
    }
}

