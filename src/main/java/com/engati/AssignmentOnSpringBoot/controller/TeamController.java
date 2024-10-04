package com.engati.AssignmentOnSpringBoot.controller;

import com.engati.AssignmentOnSpringBoot.dto.TeamDTO;
import com.engati.AssignmentOnSpringBoot.entity.Team;
import com.engati.AssignmentOnSpringBoot.service.EmployeeService;
import com.engati.AssignmentOnSpringBoot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    public TeamService teamService;

    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/team")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/team/{Id}")
    public Team getTeamById(@PathVariable Integer Id){
        return teamService.getTeamById(Id);
    }

    @PostMapping("/team")
    public TeamDTO postIntoTeam(@RequestBody TeamDTO teamDTO){
        return teamService.postIntoTeam(teamDTO);
    }

    @PutMapping("/team")
    public TeamDTO putIntoTeam(@RequestBody TeamDTO teamDTO){
        return teamService.putIntoTeam(teamDTO);
    }

    @DeleteMapping("/team/{Id}")
    public void deleteFromTeam(@PathVariable Integer Id){
        teamService.deleteFromTeam(Id);
    }

}
