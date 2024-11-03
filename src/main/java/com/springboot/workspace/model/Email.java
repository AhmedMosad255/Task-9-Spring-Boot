package com.springboot.workspace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Email {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String content;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
