package com.engati.AssignmentOnSpringBoot.controller;

import com.engati.AssignmentOnSpringBoot.dto.EmployeeDTO;
import com.engati.AssignmentOnSpringBoot.dto.EmployeeDTOWithName;
import com.engati.AssignmentOnSpringBoot.service.EmployeeService;
import com.engati.AssignmentOnSpringBoot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    public TeamService teamService;

    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllDetails(){
        return employeeService.getAllDetails();
    }

    @GetMapping("/employee/{Id}")
    public EmployeeDTOWithName getDetailsById(@PathVariable Integer Id){
        return employeeService.getDetailsById(Id);
    }

    @PostMapping("/employee")
    public EmployeeDTOWithName postIntoEmployees(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.postIntoEmployees(employeeDTO);
    }

    @PutMapping("/employee")
    public EmployeeDTOWithName putIntoEmployees(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.putIntoEmployees(employeeDTO);
    }

    @DeleteMapping("/employee/{Id}")
    public void deleteFromEmployees(@PathVariable Integer Id){
        employeeService.deleteFromEmployees(Id);
    }

}
