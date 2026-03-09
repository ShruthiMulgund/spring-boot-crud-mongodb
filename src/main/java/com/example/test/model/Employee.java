package com.example.test.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "employees")
@Data
@Builder
public class Employee {

    @Id
    private String id;
    @Field(name = "employee_name")
    private String employeeName;
    private String location;
    private BigDecimal salary;
}
