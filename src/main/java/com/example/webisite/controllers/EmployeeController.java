package com.example.webisite.controllers;

import com.example.webisite.models.Employee;
import com.example.webisite.services.imple.IEmployeeService;
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

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("")
    public String renderListEmployeePage(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("/create")
    public String renderCreateEmployeePage() {
        return "employees/create";
    }

    @PostMapping("/create")
    public String createEmployee(Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/update")
    public String renderUpdateEmployeePage(@PathVariable("id") Long id, Model model) {
        System.out.println("1");
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

    @GetMapping("/{keyword}/search")
    public String SearchEmployee(String keyword, Model model) {
        List<Employee> employees = employeeService.findEmployeesByName(keyword);
        model.addAttribute("employees", employees);
        return "employees/list";
    }
    @GetMapping("/{id}/full")
    public String customersFull(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "customers/full";
    }

//    @GetMapping("/search")
//    public String searchEmployee(String keyword, Model model) {
//        List<Employee> employees = employeeService.findEmployeesByNameOrPhoneNumber(keyword);
//        model.addAttribute("employees", employees);
//        return "customers/list";
//    }

    @GetMapping("/{id}/book")
    public String bookEmployee(@PathVariable("id") long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.setStatus(true);
        this.employeeService.updateEmployee(employee);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/cancel")
    public String cancelBooking(@PathVariable("id") long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.setStatus(false);
        this.employeeService.updateEmployee(employee);
        return "redirect:/customers";
    }
}
