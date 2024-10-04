package com.engati.AssignmentOnSpringBoot.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;
    @Column(unique = true)
    private String teamName;

    @Column(nullable = true)
    private String teamDescription;
    @OneToMany(mappedBy = "team")
    @Column(nullable = true)
    private List<Employee> employee;

    public Team(Integer teamId, String teamName, String teamDescription, List<Employee> employee) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.employee = employee;
    }

    public Team() {
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamDescription='" + teamDescription + '\'' +
                ", employee=" + employee +
                '}';
    }
}
