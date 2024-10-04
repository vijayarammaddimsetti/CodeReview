package com.engati.AssignmentOnSpringBoot.repository;

import com.engati.AssignmentOnSpringBoot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsByEmployeeEmailId(String employeeEmailId);
}
