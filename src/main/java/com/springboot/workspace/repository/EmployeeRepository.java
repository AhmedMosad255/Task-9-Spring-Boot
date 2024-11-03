package com.springboot.workspace.repository;

import com.springboot.workspace.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameIn(List<String> names);
}
