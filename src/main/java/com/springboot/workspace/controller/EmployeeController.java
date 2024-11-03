package com.springboot.workspace.controller;

import com.springboot.workspace.dto.EmployeeDTO;
import com.springboot.workspace.response.ApiResponse;
import com.springboot.workspace.service.employee.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createEmployee(@Valid @RequestBody EmployeeDTO employeeDto) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok(new ApiResponse("Add employee successfully", createdEmployee));
    }
    @PutMapping("/{employeeId}/update")
    public ResponseEntity<ApiResponse> updateEmployee(
            @PathVariable Integer employeeId,
            @Valid @RequestBody EmployeeDTO employeeDto
    ) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
        return ResponseEntity.ok(new ApiResponse("Update employee successfully!", updatedEmployee));
    }
    @DeleteMapping("/{employeeId}/delete")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(new ApiResponse("Delete Employee Successfully!", employeeId));
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ApiResponse("Get All Employees", employeeDTOList));
    }
    @GetMapping("/{employeeId}/get-by-id")
    public ResponseEntity<ApiResponse> getEmployeeById(@PathVariable Integer employeeId) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(new ApiResponse("Get Employee Successfully", employeeDTO));
    }
    @PostMapping("/by-ids")
    public ResponseEntity<ApiResponse> getEmployeesByIds(@RequestBody List<Integer> employeeIds) {
        List<EmployeeDTO> employeeDTOList = employeeService.getEmployeesByIds(employeeIds);
        return ResponseEntity.ok(new ApiResponse("Get All Employees", employeeDTOList));
    }
    @PostMapping("/by-names")
    public ResponseEntity<ApiResponse> getEmployeesByNames(@RequestBody List<String> name) {
        List<EmployeeDTO> employeeDTOList = employeeService.getEmployeesByNames(name);
        return ResponseEntity.ok(new ApiResponse("Get All Employees", employeeDTOList));
    }

}
