package com.springboot.workspace.service.employee;

import com.springboot.workspace.dto.EmployeeDTO;
import com.springboot.workspace.mapper.EmployeeMapper;
import com.springboot.workspace.model.Employee;
import com.springboot.workspace.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // convert dto to entity
        Employee employee = EmployeeMapper.INSTANCE.toEmployee(employeeDTO);
        // save to repository
        Employee savedEmployee = employeeRepository.save(employee);
        // convert entity to dto
        return EmployeeMapper.INSTANCE.toEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        // find existing employee
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        // update employee
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setAge(employeeDTO.getAge());
        existingEmployee.setSalary(employeeDTO.getSalary());
        // save updates
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return EmployeeMapper.INSTANCE.toEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.findById(id).ifPresentOrElse(employeeRepository::delete, () -> {
            throw new RuntimeException("Employee not found");
        });
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper.INSTANCE::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return EmployeeMapper.INSTANCE.toEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByIds(List<Integer> ids) {
        return employeeRepository.findAllById(ids)
                .stream()
                .map(EmployeeMapper.INSTANCE::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByNames(List<String> names) {
        return employeeRepository.findByNameIn(names)
                .stream()
                .map(EmployeeMapper.INSTANCE::toEmployeeDTO)
                .collect(Collectors.toList());
    }

}
