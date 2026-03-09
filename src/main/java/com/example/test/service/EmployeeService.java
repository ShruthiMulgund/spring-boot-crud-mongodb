package com.example.test.service;

import com.example.test.dto.EmployeeDTO;
import com.example.test.model.Employee;
import com.example.test.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String createEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee emp = Employee.builder()
                    .employeeName(employeeDTO.getEmployeeName())
                    .salary(employeeDTO.getSalary())
                    .location(employeeDTO.getLocation()).build();
            employeeRepository.save(emp);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Employee created successfully!";
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeeByLocation(String location) {
       return employeeRepository.findByLocation(location);
    }

    public String deleteEmployee(String id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Employee Deleted Successfully!";
    }

    public String updateEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee employee = Employee.builder()
                    .id(employeeDTO.getId())
                    .employeeName(employeeDTO.getEmployeeName())
                    .location(employeeDTO.getLocation())
                    .salary(employeeDTO.getSalary())
                    .build();
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "Updated details of Employee Successfully";

    }
}
