package com.example.webisite.services;

import com.example.webisite.models.Customer;
import com.example.webisite.repositories.CustomerRepository;
import com.example.webisite.services.imple.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findCustomerByUsername(String name) {
        return this.customerRepository.findCustomerByName(name);
    }
}
