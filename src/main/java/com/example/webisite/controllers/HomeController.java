package com.example.webisite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("")
    public String home() {
        return "home";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
    @PostMapping("")
    public String handleRegistration(@RequestParam String name,
                                     @RequestParam String number,
                                     @RequestParam String age) {
        return "redirect:/home";
    }

}
