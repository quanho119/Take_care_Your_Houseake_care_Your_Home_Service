package com.example.webisite.services.imple;

import com.example.webisite.models.Booking;
import com.example.webisite.models.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void createCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    Customer findCustomerById(Long id);
}
