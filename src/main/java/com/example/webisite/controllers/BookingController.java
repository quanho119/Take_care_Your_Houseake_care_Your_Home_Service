package com.example.webisite.controllers;

import com.example.webisite.models.Booking;
import com.example.webisite.models.Employee;
import com.example.webisite.models.Service;
import com.example.webisite.services.imple.IBookingService;
import com.example.webisite.services.imple.IEmployeeService;
import com.example.webisite.services.imple.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private IBookingService bookingService;
    public BookingController(IEmployeeService employeeService, IServiceService serviceService, IBookingService bookingService) {
        this.employeeService = employeeService;
        this.serviceService = serviceService;
        this.bookingService = bookingService;
    }

    @GetMapping("")
    public String renderListBookingsPage(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings/list";
    }

    @GetMapping("/{id}/book")
    public String renderBookPage(@PathVariable("id") long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("employee", employee);
        model.addAttribute("services", services);
        model.addAttribute("booking", new Booking());
        return "bookings/create";
    }

    @PostMapping("/book")
    public String bookEmployee(@ModelAttribute("booking")Booking booking, Model model) {
        bookingService.createBooking(booking);
        Employee employee = employeeService.findEmployeeById(booking.getEmployee().getId());
        employee.setStatus(false);
        this.employeeService.updateEmployee(employee);
        return "redirect:/bookings";
    }

    @GetMapping("/{id}/delete")
    public String cancelBooking(@PathVariable("id") long id) {
        Employee employee = employeeService.findEmployeeById(id);
        employee.setStatus(true);
        this.employeeService.updateEmployee(employee);
        Booking booking = bookingService.findBookingById(id);
        bookingService.deleteBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/{id}/update")
    public String renderUpdatePage(@PathVariable("id") long id, Model model) {
        Booking booking = bookingService.findBookingById(id);
        model.addAttribute("booking", booking);
        return "bookings/update";
    }
}
