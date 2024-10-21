package com.example.webisite.controllers;

import com.example.webisite.models.Admin;
import com.example.webisite.services.imple.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("")
    public String renderListAdminPage(Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admins/list";
    }

    @GetMapping("/create")
    public String renderCreateAdminPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "admins/create";
    }

    @PostMapping("/create")
    public String createAdmin(@ModelAttribute("admin") Admin admin) {
        adminService.createAdmin(admin);
        return "redirect:/admins";
    }

    @GetMapping("/{id}/update")
    public String renderUpdateAdminPage(@PathVariable("id") Long id, Model model) {
        Admin admin = adminService.findAdminById(id);
        model.addAttribute("admin", admin);
        return "admins/update";
    }

    @PostMapping("/update")
    public String updateAdmin(@ModelAttribute("admin") Admin admin) {
        adminService.updateAdmin(admin);
        return "redirect:/admins";
    }

    @GetMapping("/{id}/delete")
    public String deleteAdmin(@PathVariable("id") Long id) {
        Admin admin = adminService.findAdminById(id);
        adminService.deleteAdmin(admin);
        return "redirect:/admins";
    }


    @GetMapping("/{id}/full")
    public String adminFull(@PathVariable("id") Long id, Model model) {
        Admin admin = adminService.findAdminById(id);
        model.addAttribute("admin", admin);
        return "admins/full";
    }
}
