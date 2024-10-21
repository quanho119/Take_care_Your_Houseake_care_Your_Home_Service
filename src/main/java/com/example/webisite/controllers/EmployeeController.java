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
        return "redirect:/employees";
    }

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
    public String customersFull(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/full";
    }
//    @GetMapping("/{id}/book")
//    public String renderBookPage(@PathVariable("id") long id, Model model) {
//        Employee employee = employeeService.findEmployeeById(id);
//        List<Service> services = serviceService.getAllServices();
//        model.addAttribute("employee", employee);
//        model.addAttribute("services", services);
//        model.addAttribute("booking", new Booking());
//        return "employees/booking";
//    }
//
//    @PostMapping("/book")
//    public String bookEmployee(@RequestParam("employeeId") Long id, @ModelAttribute("booking")Booking booking, Model model) {
//        Employee employee = employeeService.findEmployeeById(id);
//
//        employee.setStatus(false);
//        this.employeeService.updateEmployee(employee);
//        return "redirect:/employees";
//    }
//
//    @GetMapping("/{id}/cancel")
//    public String cancelBooking(@PathVariable("id") long id) {
//        Employee employee = employeeService.findEmployeeById(id);
//        employee.setStatus(true);
//        this.employeeService.updateEmployee(employee);
//        return "redirect:/employees";
//    }
}
