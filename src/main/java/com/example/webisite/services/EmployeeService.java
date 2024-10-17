package com.example.webisite.services;

import com.example.webisite.models.Employee;
import com.example.webisite.repositories.EmployeeRepository;
import com.example.webisite.services.imple.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findEmployeesByName(String keyword) {
        return this.employeeRepository.findByNameContaining(keyword);
    }

//    @Override
//    public List<Employee> findEmployeesByNameOrPhoneNumber(String keyword){
//        return this.employeeRepository.findByPhoneNumberContainingOrNameContaining(keyword);
//    }
}
