package com.example.emsbackendv2.repository;

import com.example.emsbackendv2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
