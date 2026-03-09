package com.example.test.service;

import com.example.test.dto.EmployeeDTO;
import com.example.test.model.Employee;
import com.example.test.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee() {
        Employee employeeOne = Employee.builder()
                .employeeName("Lilith")
                .location("Hell")
                .salary(BigDecimal.valueOf(6666))
                .build();

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeName("Bobby")
                .location("Sioux Falls")
                .salary(BigDecimal.valueOf(777))
                .build();

        when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenReturn(employeeOne);

        String savedEmployee = employeeService.createEmployee(employeeDTO);

        Assertions.assertThat(savedEmployee).isNotEmpty();

    }

    @Test
    void testGetEmployees() {
        // Arrange
        Employee employeeOne = Employee.builder()
                .employeeName("Jody")
                .location("Sioux Falls")
                .salary(BigDecimal.valueOf(8744))
                .build();

        List<Employee> employees = List.of(employeeOne);

        when(employeeRepository.findAll()).thenReturn(employees);

        // Act
        List<Employee> result = employeeService.getEmployees();

        // Assert
        org.junit.jupiter.api.Assertions.assertNotNull(result);
        org.junit.jupiter.api.Assertions.assertEquals(1, result.size());
        org.junit.jupiter.api.Assertions.assertEquals("Jody", result.getFirst().getEmployeeName());
    }


}
