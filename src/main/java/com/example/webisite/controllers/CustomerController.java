package com.example.webisite.controllers;

import com.example.webisite.models.Customer;
import com.example.webisite.models.Service;
import com.example.webisite.services.imple.ICustomerService;
import com.example.webisite.services.imple.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IServiceService serviceService;

    public CustomerController(ICustomerService customerService, IServiceService serviceService) {
        this.customerService = customerService;
        this.serviceService = serviceService;
    }

    @GetMapping("")
    public String renderListCustomerPage(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers/list";
    }

    @GetMapping("/create")
    public String renderCreateCustomerPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/create";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/update")
    public String renderUpdateCustomerPage(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "customers/update";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        customerService.deleteCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/full")
    public String customersFull(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "customers/full";
    }

//    @GetMapping("/{id}/book")
//    public String renderBookPage(@PathVariable("id") long id, Model model) {
//        Customer customer = customerService.findCustomerById(id);
//        List<Service> services = serviceService.getAllServices();
//        model.addAttribute("customer", customer);
//        model.addAttribute("services", services);
//        model.addAttribute("booking", new Booking());
//        return "customers/booking";
//    }
//
//    @PostMapping("/book")
//    public String bookCustomer(@RequestParam("customerId") Long id, @ModelAttribute("booking") Booking booking, Model model) {
//        Customer customer = customerService.findCustomerById(id);
//        customer.setStatus(false);
//        this.customerService.updateCustomer(customer);
//        return "redirect:/customers";
//    }
//
//    @GetMapping("/{id}/cancel")
//    public String cancelBooking(@PathVariable("id") long id) {
//        Customer customer = customerService.findCustomerById(id);
//        customer.setStatus(true);
//        this.customerService.updateCustomer(customer);
//        return "redirect:/customers";
//    }
}
