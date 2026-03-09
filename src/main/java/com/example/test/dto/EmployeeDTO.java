package com.example.test.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class EmployeeDTO {
    private String id;
    private String employeeName;
    private String location;
    private BigDecimal salary;
}
