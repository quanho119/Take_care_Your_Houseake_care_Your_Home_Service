package com.example.webisite.services.imple;

import com.example.webisite.models.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();

    void updateEmployee(Employee employee);

    void createEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee findEmployeeById(Long id);

    List<Employee> findEmployeesByName(String keyword);

//    List<Employee> findEmployeesByNameOrPhoneNumber(String keyword);

}
