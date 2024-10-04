package com.engati.AssignmentOnSpringBoot.service;

import com.engati.AssignmentOnSpringBoot.dto.TeamDTO;
import com.engati.AssignmentOnSpringBoot.entity.Team;
import com.engati.AssignmentOnSpringBoot.exception.DuplicateException;
import com.engati.AssignmentOnSpringBoot.exception.NotFoundException;
import com.engati.AssignmentOnSpringBoot.repository.EmployeeRepository;
import com.engati.AssignmentOnSpringBoot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    public TeamRepository teamRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    public TeamDTO convertToDTO(Team team){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamDTOId(team.getTeamId());
        teamDTO.setTeamDTOName(team.getTeamName());
        teamDTO.setTeamDTODescription(team.getTeamDescription());
        return teamDTO;
    }

    public Team convertTo(TeamDTO teamDTO){
        Team team = new Team();
        team.setTeamDescription(teamDTO.getTeamDTODescription());
        if(teamRepository.existsByTeamName(teamDTO.getTeamDTOName())){
            throw new DuplicateException("Team Name Already Exists.. Enter a new One");
        }
        team.setTeamName(teamDTO.getTeamDTOName());
        return team;
    }

    public Team getTeamById(Integer id) {
        if(!teamRepository.existsById(id)){
            throw new NotFoundException("Given team with ID is not Present.");
        }
        return teamRepository.findById(id).get();
    }

    public List<Team> getAllTeams() {
        List<Team> team = teamRepository.findAll().stream().toList();
        if(team.isEmpty()){
            throw new NotFoundException("No Team is available");
        }
        return team;
    }

    public TeamDTO postIntoTeam(TeamDTO teamDTO) {
        Team team = this.convertTo(teamDTO);
        teamRepository.save(team);
        if (team.getTeamId() == null) {
            throw new NotFoundException("Failed to create team, ID is null");
        }
        teamDTO.setTeamDTOId(team.getTeamId());
        return teamDTO;
    }

    public TeamDTO putIntoTeam(TeamDTO teamDTO) {
        if(!teamRepository.existsById(teamDTO.getTeamDTOId())){
            throw new NotFoundException("Team Id" + teamDTO.getTeamDTOId() + " not found.." + " Enter a valid Team ID");
        }
        Team team = teamRepository.findById(teamDTO.getTeamDTOId()).get();
        team.setTeamDescription(teamDTO.getTeamDTODescription());
        team.setTeamName(teamDTO.getTeamDTOName());
        teamRepository.save(team);
        team.getEmployee().forEach(employee -> employee.setTeam(team));
        return teamDTO;
    }

    public void deleteFromTeam(Integer id) {
        if(teamRepository.findById(id).get().getEmployee().isEmpty()){
           teamRepository.delete(teamRepository.findById(id).get());
        }
        else {
            throw new DuplicateException("Team with given Id is not Empty");
        }
    }

}
