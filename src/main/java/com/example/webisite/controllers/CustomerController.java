package com.example.webisite.controllers;

import com.example.webisite.models.Employee;
import com.example.webisite.services.imple.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
     private IEmployeeService employeeService;

    public CustomerController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("")
    public String customers(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        return "customers/list";
    }
    @GetMapping("/{id}/full")
    public String customersFull(Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "customers/full";
    }

    @GetMapping("/search")
    public String searchEmployee(String keyword, Model model) {
        List<Employee> employees = employeeService.findEmployeesByNameOrPhoneNumber(keyword);
        model.addAttribute("employees", employees);
        return "customers/list";
    }

    @GetMapping("/{id}/book")
    public String bookEmployee(long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.setStatus(true);
        this.employeeService.updateEmployee(employee);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/cancel")
    public String cancelBooking(long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.setStatus(false);
        this.employeeService.updateEmployee(employee);
        return "redirect:/customers";
    }
}
