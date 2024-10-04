package com.engati.AssignmentOnSpringBoot.dto;

public class EmployeeDTO {

    private Integer employeeDTOId;
    private String employeeDTOName;
    private String employeeDTOEmailId;
    private String employeeDTOJobTitle;
    private Integer employeeTeamId;

    public EmployeeDTO(Integer employeeDTOId, String employeeDTOName, String employeeDTOEmailId, String employeeDTOJobTitle, Integer employeeTeamId) {
        this.employeeDTOId = employeeDTOId;
        this.employeeDTOName = employeeDTOName;
        this.employeeDTOEmailId = employeeDTOEmailId;
        this.employeeDTOJobTitle = employeeDTOJobTitle;
        this.employeeTeamId = employeeTeamId;
    }

    public EmployeeDTO() {
    }

    public Integer getEmployeeDTOId() {
        return employeeDTOId;
    }

    public void setEmployeeDTOId(Integer employeeDTOId) {
        this.employeeDTOId = employeeDTOId;
    }

    public String getEmployeeDTOName() {
        return employeeDTOName;
    }

    public void setEmployeeDTOName(String employeeDTOName) {
        this.employeeDTOName = employeeDTOName;
    }

    public String getEmployeeDTOEmailId() {
        return employeeDTOEmailId;
    }

    public void setEmployeeDTOEmailId(String employeeDTOEmailId) {
        this.employeeDTOEmailId = employeeDTOEmailId;
    }

    public String getEmployeeDTOJobTitle() {
        return employeeDTOJobTitle;
    }

    public void setEmployeeDTOJobTitle(String employeeDTOJobTitle) {
        this.employeeDTOJobTitle = employeeDTOJobTitle;
    }

    public Integer getEmployeeTeamId() {
        return employeeTeamId;
    }

    public void setEmployeeTeamId(Integer employeeTeamId) {
        this.employeeTeamId = employeeTeamId;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeDTOId=" + employeeDTOId +
                ", employeeDTOName='" + employeeDTOName + '\'' +
                ", employeeDTOEmailId='" + employeeDTOEmailId + '\'' +
                ", employeeDTOJobTitle='" + employeeDTOJobTitle + '\'' +
                ", employeeTeamId=" + employeeTeamId +
                '}';
    }
}
