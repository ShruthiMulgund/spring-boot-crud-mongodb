package com.example.test.repository;

import com.example.test.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataMongoTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void savedEmployees() {
        Employee employeeDTO = Employee.builder()
                .employeeName("Kevin")
                .location("Princeton")
                .salary(BigDecimal.valueOf(777777))
                .build();

        Employee employee = employeeRepository.save(employeeDTO);

        Assertions.assertNotNull(employee);
        org.assertj.core.api.Assertions.assertThat(employee.getSalary().compareTo(BigDecimal.ZERO));
    }

    @Test
    public void findEmployees() {

        Employee employeeOne = Employee.builder()
                .employeeName("Kevin")
                .location("Princeton")
                .salary(BigDecimal.valueOf(777777))
                .build();

        Employee employeeTwo = Employee.builder()
                .employeeName("Metatron")
                .location("Missouri")
                .salary(BigDecimal.valueOf(3517))
                .build();

        employeeRepository.save(employeeOne);
        employeeRepository.save(employeeTwo);

        List<Employee> employeeList = employeeRepository.findAll();

        Assertions.assertNotNull(employeeList);
        Assertions.assertEquals(4, employeeList.size());

    }

    @Test
    public void findEmployeeByID() {
        Employee employeeOne = Employee.builder()
                .employeeName("Kevin")
                .location("Princeton")
                .salary(BigDecimal.valueOf(777777))
                .build();

        employeeRepository.save(employeeOne);

        Optional<Employee> emp = employeeRepository.findById(employeeOne.getId());

        Assertions.assertNotNull(emp);
        Assertions.assertEquals("Kevin", emp.get().getEmployeeName());
    }

    @Test
    public void findEmployeesByLocation() {
        Employee employeeOne = Employee.builder()
                .employeeName("Mary")
                .location("Kansas")
                .salary(BigDecimal.valueOf(777))
                .build();

        employeeRepository.save(employeeOne);

        List<Employee> emp = employeeRepository.findByLocation(employeeOne.getLocation());

        Assertions.assertEquals(1, emp.size());
    }

    @Test
    public void checkUpdatedEntry() {
        Employee employeeOne = Employee.builder()
                .employeeName("John")
                .location("Kansas")
                .salary(BigDecimal.valueOf(777))
                .build();

        employeeRepository.save(employeeOne);

        Employee employee = employeeRepository.findById(employeeOne.getId()).get();
        employee.setEmployeeName("Adam");
        employee.setLocation("Missouri");
        employee.setSalary(BigDecimal.valueOf(787878));

        Employee updatedEmployee = employeeRepository.save(employee);

        Assertions.assertNotNull(updatedEmployee.getEmployeeName());
        Assertions.assertNotNull(updatedEmployee.getLocation());
        Assertions.assertNotNull(updatedEmployee.getSalary());
    }

    @Test
    public void deleteEmployeeById() {
        Employee employeeOne = Employee.builder()
                .employeeName("Lilith")
                .location("Hell")
                .salary(BigDecimal.valueOf(6666))
                .build();

        employeeRepository.save(employeeOne);
        employeeRepository.deleteById(employeeOne.getId());
        Optional<Employee> employee = employeeRepository.findById(employeeOne.getId());

        org.assertj.core.api.Assertions.assertThat(employee).isEmpty();




    }

}
