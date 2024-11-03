package com.springboot.workspace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailDto {
    private Integer id;
    @NotNull(message = "Name must not be null")
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @Email(message = "content must be valid email")
    private String content;
    private Integer employeeId;

}
