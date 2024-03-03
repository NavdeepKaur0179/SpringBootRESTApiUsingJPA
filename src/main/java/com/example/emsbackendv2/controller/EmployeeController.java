package com.example.emsbackendv2.controller;

import com.example.emsbackendv2.dto.EmployeeDto;
import com.example.emsbackendv2.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    EmployeeService employeeService;

    //Build add employee REST api
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Get Employee By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id")  Long employeeId)
    {
        EmployeeDto employeeById=employeeService.getEmployeeById(employeeId);
//        if no exception class handling null
//        if (employeeById == null) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
        return ResponseEntity.ok(employeeById);
    }

    //get All employees REST API

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees()
    {
        List<EmployeeDto> employees=employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //build update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto updatedEmployeeDto)
    {
        EmployeeDto updatedEmployee=employeeService.updateEmployee(updatedEmployeeDto.getId(),updatedEmployeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }
    //Build delete employee REST Api

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Successfully!");
    }
}
