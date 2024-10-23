package com.example.webisite.controllers;

import ch.qos.logback.core.LayoutBase;
import com.example.webisite.models.Booking;
import com.example.webisite.models.Customer;
import com.example.webisite.models.Employee;
import com.example.webisite.models.Service;
import com.example.webisite.services.imple.IBookingService;
import com.example.webisite.services.imple.ICustomerService;
import com.example.webisite.services.imple.IEmployeeService;
import com.example.webisite.services.imple.IServiceService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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

    @Autowired
    private ICustomerService customerService;
    public BookingController(IEmployeeService employeeService, IServiceService serviceService, IBookingService bookingService, ICustomerService customerService) {
        this.employeeService = employeeService;
        this.serviceService = serviceService;
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    @GetMapping("")
    public String renderListBookingsPage(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings/list";
    }

    @GetMapping("/create")
    public String renderCreateBookingPage(Model model) {

        Booking booking = new Booking();
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        Customer loggedCustomer = customerService.findCustomerByUsername(username);
        Customer loggedCustomer = customerService.findCustomerById(1L);
        booking.setCustomer(loggedCustomer);
        List<Employee> employees = employeeService.getAllEmployees();
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("employees", employees);
        model.addAttribute("services", services);
        model.addAttribute("booking", booking);
        return "bookings/create";
    }

    @GetMapping("/{id}/create")
    public String renderBookPage(@PathVariable("id") long id, Model model) {
        Booking booking = new Booking();
        Employee employee = employeeService.findEmployeeById(id);
        List<Service> services = serviceService.getAllServices();
        Customer loggedCustomer = customerService.findCustomerById(1L);
        booking.setCustomer(loggedCustomer);
        model.addAttribute("employee", employee);
        model.addAttribute("services", services);
        model.addAttribute("booking", booking);
        return "bookings/create";
    }

    @PostMapping("/create")
    public String bookEmployee(@ModelAttribute("booking") Booking booking, Model model) {
        Employee employee = employeeService.findEmployeeById(booking.getEmployee().getId());
        Customer customer = customerService.findCustomerById(booking.getCustomer().getId());
        employee.setStatus(false);
        booking.setEmployee(employee);
        booking.setCustomer(customer);
        bookingService.createBooking(booking);

        return "redirect:/bookings";
    }

    @GetMapping("/{id}/delete")
    public String cancelBooking(@PathVariable("id") long id) {
        Booking booking = bookingService.findBookingById(id);
        Employee employee = booking.getEmployee();
        employee.setStatus(true);
        this.employeeService.updateEmployee(employee);
        bookingService.deleteBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/{id}/update")
    public String renderUpdatePage(@PathVariable("id") long id, Model model) {
        Booking booking = bookingService.findBookingById(id);
        Employee employee = employeeService.findEmployeeById(id);
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("booking", booking);
        model.addAttribute("employee", employee);
        model.addAttribute("services", services);
        return "bookings/update";
    }

    @PostMapping("/update")
    public String updateBooking(@ModelAttribute("booking") Booking booking) {
        Employee employee = employeeService.findEmployeeById(booking.getEmployee().getId());
        Customer customer = customerService.findCustomerById(booking.getCustomer().getId());
        employee.setStatus(false);
        booking.setEmployee(employee);
        booking.setCustomer(customer);
        bookingService.updateBooking(booking);
        return "redirect:/bookings";
    }
}
