package com.engati.AssignmentOnSpringBoot.dto;

public class EmployeeDTOWithName {
    private int employeeDTOId;
    private String employeeDTOName;
    private String employeeDTOEmailId;
    private String employeeDTOJobTitle;
    private String getEmployeeDTOTeamName;

    public EmployeeDTOWithName(int employeeDTOId, String employeeDTOName, String employeeDTOEmailId, String getEmployeeDTOTeamName, String employeeDTOJobTitle) {
        this.employeeDTOId = employeeDTOId;
        this.employeeDTOName = employeeDTOName;
        this.employeeDTOEmailId = employeeDTOEmailId;
        this.getEmployeeDTOTeamName = getEmployeeDTOTeamName;
        this.employeeDTOJobTitle = employeeDTOJobTitle;
    }

    public EmployeeDTOWithName() {
    }

    public int getEmployeeDTOId() {
        return employeeDTOId;
    }

    public void setEmployeeDTOId(int employeeDTOId) {
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

    public String getGetEmployeeDTOTeamName() {
        return getEmployeeDTOTeamName;
    }

    public void setGetEmployeeDTOTeamName(String getEmployeeDTOTeamName) {
        this.getEmployeeDTOTeamName = getEmployeeDTOTeamName;
    }

    @Override
    public String toString() {
        return "EmployeeDTOWithName{" +
                "employeeDTOId=" + employeeDTOId +
                ", employeeDTOName='" + employeeDTOName + '\'' +
                ", employeeDTOEmailId='" + employeeDTOEmailId + '\'' +
                ", employeeDTOJobTitle='" + employeeDTOJobTitle + '\'' +
                ", getEmployeeDTOTeamName='" + getEmployeeDTOTeamName + '\'' +
                '}';
    }
}
