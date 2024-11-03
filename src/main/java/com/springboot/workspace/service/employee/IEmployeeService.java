package com.springboot.workspace.service.employee;

import com.springboot.workspace.dto.EmployeeDTO;

import java.util.List;

public interface IEmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO);
    void deleteEmployee(Integer id);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Integer id);
    List<EmployeeDTO> getEmployeesByIds(List<Integer> ids);
    List<EmployeeDTO> getEmployeesByNames(List<String> names);
}
