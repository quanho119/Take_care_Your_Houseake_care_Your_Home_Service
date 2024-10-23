package com.example.webisite.repositories;

import com.example.webisite.models.Booking;
import com.example.webisite.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByName(String name);

}
