package com.example.webisite.repositories;

import com.example.webisite.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String keyword);
//    List<Employee> findByPhoneNumberContainingOrNameContaining(String keyword);
}
