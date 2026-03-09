package com.example.test.controller;

import com.example.test.dto.EmployeeDTO;
import com.example.test.model.Employee;
import com.example.test.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    public void init() {
        employee = Employee.builder()
                .employeeName("Amara")
                .location("Empty")
                .salary(BigDecimal.valueOf(78566))
                .build();
    }

    @Test
    public void createEmployee() throws Exception {

        EmployeeDTO emp = EmployeeDTO.builder()
                .employeeName("Chuck")
                .location("Unknown")
                .salary(BigDecimal.valueOf(666))
                .build();

        when(employeeService.createEmployee(emp)).thenReturn("Employee created successfully");

        mockMvc.perform(post("/api/emp/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(emp)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Employee created successfully"));

        verify(employeeService, times(1)).createEmployee(any(EmployeeDTO.class));
    }

    @Test
    void getAllEmployees() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(employee);

        when(employeeService.getEmployees()).thenReturn(mockEmployees);

        mockMvc.perform(get("/api/emp"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].employeeName").value("Amara"));

        // Verify service interaction
        verify(employeeService, times(1)).getEmployees();
    }

    @Test
    void getEmployeesByLocation()  throws Exception {
        String location = "Empty";

        when(employeeService.getEmployeeByLocation(location))
                .thenReturn(Collections.singletonList(employee));

        mockMvc.perform(get("/api/emp/location")
                        .param("location", location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeName").value("Amara"));

        verify(employeeService, times(1)).getEmployeeByLocation(location);
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateEmployeeDetails() {
    }
}