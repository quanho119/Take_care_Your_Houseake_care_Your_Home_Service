package com.example.webisite.repositories;

import com.example.webisite.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String keyword);
    List<Employee> findByPhoneNumberContainingOrNameContaining(String keyword1, String keyword2);

}
