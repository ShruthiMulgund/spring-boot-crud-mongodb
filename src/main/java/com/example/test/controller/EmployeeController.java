package com.example.test.controller;

import com.example.test.dto.EmployeeDTO;
import com.example.test.model.Employee;
import com.example.test.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.createEmployee(employee);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/location")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByLocation(@RequestParam String location) {
        return employeeService.getEmployeeByLocation(location);
    }

   @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@RequestParam String id){
        return employeeService.deleteEmployee(id);
   }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateEmployeeDetails(@RequestBody EmployeeDTO employeeDTO) {
            return employeeService.updateEmployee(employeeDTO);

    }

}
