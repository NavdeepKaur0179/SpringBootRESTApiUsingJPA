package com.example.emsbackendv2.service.impl;

import com.example.emsbackendv2.dto.EmployeeDto;
import com.example.emsbackendv2.entity.Employee;
import com.example.emsbackendv2.exception.ResourceNotFoundException;
import com.example.emsbackendv2.mapper.EmployeeMapper;
import com.example.emsbackendv2.repository.EmployeeRepository;
import com.example.emsbackendv2.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employeeById=employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee id does not exist : "+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employeeById);

    }

    @GetMapping
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee>  employees=employeeRepository.findAll();
        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("No record exist with this employee id "+ employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee employeeUpdated=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(employeeUpdated);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("No record exist with this employee id "+ employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
