package com.engati.AssignmentOnSpringBoot.service;

import com.engati.AssignmentOnSpringBoot.dto.EmployeeDTO;
import com.engati.AssignmentOnSpringBoot.dto.EmployeeDTOWithName;
import com.engati.AssignmentOnSpringBoot.entity.Employee;
import com.engati.AssignmentOnSpringBoot.entity.Team;
import com.engati.AssignmentOnSpringBoot.exception.DuplicateException;
import com.engati.AssignmentOnSpringBoot.exception.NotCorrectFormatException;
import com.engati.AssignmentOnSpringBoot.exception.NotFoundException;
import com.engati.AssignmentOnSpringBoot.repository.EmployeeRepository;
import com.engati.AssignmentOnSpringBoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeService {

    @Autowired
    public TeamRepository teamRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|in)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public EmployeeDTO convertToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeDTOId(employee.getEmployeeId());
        employeeDTO.setEmployeeDTOEmailId(employee.getEmployeeEmailId());
        if (employee.getTeam() != null) {
            employeeDTO.setEmployeeTeamId(employee.getTeam().getTeamId());
        } else {
            employeeDTO.setEmployeeTeamId(null);
        }
        employeeDTO.setEmployeeDTOName(employee.getEmployeeName());
        employeeDTO.setEmployeeDTOJobTitle(employee.getEmployeeJobTitle());
        return employeeDTO;
    }

    public Employee convertTo(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        String email = employeeDTO.getEmployeeDTOEmailId().toLowerCase();
        if (!isValidEmail(email)) {
            throw new NotCorrectFormatException("Invalid email format: " + email);
        }
        employee.setEmployeeEmailId(email);
        employee.setEmployeeName(employeeDTO.getEmployeeDTOName());
        employee.setEmployeeJobTitle(employeeDTO.getEmployeeDTOJobTitle());
        Integer teamIds = employeeDTO.getEmployeeTeamId();
        if(teamIds != null) {
            Optional<Team> optionalTeam = teamRepository.findById(employeeDTO.getEmployeeTeamId());
            if (optionalTeam.isPresent()) {
                employee.setTeam(optionalTeam.get());
            } else {
                throw new NotFoundException("Team with ID " + employeeDTO.getEmployeeTeamId() + " not found");
            }
        }
        else {
            employee.setTeam(null);
        }
        return employee;
    }

    public List<EmployeeDTO> getAllDetails() {
        List<EmployeeDTO> employeeDTOS = employeeRepository.findAll().stream().map(this::convertToDTO).toList();
        if(employeeDTOS.isEmpty()){
            throw new NotFoundException("No Employees Found");
        }
        return employeeDTOS;
    }

    public EmployeeDTOWithName getDetailsById(Integer id) {
        if(!employeeRepository.existsById(id)){
            throw new NotFoundException("No employee Found with ID " + id);
        }
        EmployeeDTO employeeDTO = employeeRepository.findById(id).map(this::convertToDTO).get();
        return this.convertToName(employeeDTO);
    }


    public EmployeeDTOWithName postIntoEmployees(EmployeeDTO employeeDTO) {
        Employee employee = this.convertTo(employeeDTO);
        if (employeeRepository.existsByEmployeeEmailId(employee.getEmployeeEmailId())) {
            throw new DuplicateException("Employee with the same email already exists");
        }
        employeeRepository.save(employee);
        employeeDTO.setEmployeeDTOId(employee.getEmployeeId());
        return this.convertToName(employeeDTO);
    }

    public EmployeeDTOWithName putIntoEmployees(EmployeeDTO employeeDTO) {
        if(!employeeRepository.existsById(employeeDTO.getEmployeeDTOId())){
            throw new NotFoundException("Employee with given ID Doesn't Exists");
        }
        Employee employee = employeeRepository.findById(employeeDTO.getEmployeeDTOId()).get();
        employee.setEmployeeEmailId(employeeDTO.getEmployeeDTOEmailId());
        employee.setEmployeeJobTitle(employeeDTO.getEmployeeDTOJobTitle());
        employee.setEmployeeName(employeeDTO.getEmployeeDTOName());
        if(employee.getTeam() != null){
            if(Objects.equals(employee.getTeam().getTeamId(), employeeDTO.getEmployeeTeamId())){
                Team team = teamRepository.findById(employee.getTeam().getTeamId()).get();
                team.getEmployee().stream().forEach(
                        (employee1) -> {
                            if(employee1.getEmployeeId() == employee.getEmployeeId()){
                                employee1 = employee;
                            }
                        }
                );
                teamRepository.save(team);
            }
            else {
                Team team1 = teamRepository.findById(employee.getTeam().getTeamId()). get();
                Team team2 = teamRepository.findById(employeeDTO.getEmployeeTeamId()).get();
                team1.getEmployee().remove(employee);
                team2.getEmployee().add(employee);
                teamRepository.save(team1);
                teamRepository.save(team2);
                employee.setTeam(team2);
            }
        }
        employeeRepository.save(employee);
        return this.convertToName(employeeDTO);
    }

    private EmployeeDTOWithName convertToName(EmployeeDTO employeeDTO) {
        EmployeeDTOWithName employeeDTOWithName = new EmployeeDTOWithName();
        employeeDTOWithName.setEmployeeDTOId(employeeDTO.getEmployeeDTOId());
        if(employeeDTO.getEmployeeTeamId() != null){
            employeeDTOWithName.setGetEmployeeDTOTeamName(teamRepository.findById(employeeDTO.getEmployeeTeamId()).get().getTeamName());
        }
        employeeDTOWithName.setEmployeeDTOEmailId(employeeDTO.getEmployeeDTOEmailId());
        employeeDTOWithName.setEmployeeDTOJobTitle(employeeDTO.getEmployeeDTOJobTitle());
        employeeDTOWithName.setEmployeeDTOName(employeeDTO.getEmployeeDTOName());
        return employeeDTOWithName;
    }

    public void deleteFromEmployees(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No employee Found with given id " + id + " to delete"));
        if(employee.getTeam() != null && employee.getTeam().getTeamId() != null) {
            Team team = teamRepository.findById(employee.getTeam().getTeamId()).get();
            team.getEmployee().remove(employee);
            teamRepository.save(team);
        }
        employeeRepository.delete(employee);
    }
}
