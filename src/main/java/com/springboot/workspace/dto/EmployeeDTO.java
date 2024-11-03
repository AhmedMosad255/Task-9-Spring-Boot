package com.springboot.workspace.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Integer id;
    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    private String name;
    @Min(value = 16, message = "age must be greater than 15")
    @Max(value = 40, message = "age must be less than 40")
    private int age;
    @DecimalMin(value = "5000", message = "salary must be greater than 5000")
    @DecimalMax(value = "10000", message = "salary must be less than 10000")
    private Double salary;
    private List<EmailDto> emails;
}
