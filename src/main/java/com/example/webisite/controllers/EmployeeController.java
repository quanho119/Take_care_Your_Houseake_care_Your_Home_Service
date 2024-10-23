package com.example.webisite.controllers;

import com.example.webisite.models.Booking;
import com.example.webisite.models.Employee;
import com.example.webisite.models.Service;
import com.example.webisite.services.imple.IBookingService;
import com.example.webisite.services.imple.IEmployeeService;
import com.example.webisite.services.imple.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private IBookingService bookingService;
    public EmployeeController(IEmployeeService employeeService, IServiceService serviceService, IBookingService bookingService) {
        this.employeeService = employeeService;
        this.serviceService = serviceService;
        this.bookingService = bookingService;
    }

    @GetMapping("")
    public String renderListEmployeePage(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("/create")
    public String renderCreateEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/update")
    public String renderUpdateEmployeePage(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/update";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(employee);
        System.out.println(employee.getId());
        return "redirect:/employees";
    }

//    @PostMapping("/update")
//    public String updateEmployee(@ModelAttribute("employee") Employee employee, Model model) {
//        try {
//            employeeService.updateEmployee(employee);
//            return "redirect:/employees";
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("errorMessage", "Username already exists.");
//            return "employees/update";
//        }
//    }


    @GetMapping("/{id}/delete")
    public String DeleteEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employeeService.deleteEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/search")
    public String SearchEmployee(@RequestParam String keyword, Model model) {
        List<Employee> employees = employeeService.findEmployeesByNameOrPhoneNumber(keyword);
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("/{id}/full")
    public String EmployeeFull(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/full";
    }
}
