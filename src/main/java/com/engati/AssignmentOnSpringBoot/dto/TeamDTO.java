package com.engati.AssignmentOnSpringBoot.dto;

public class TeamDTO {

    //Data for Creating a new Team Initializing

    private Integer teamDTOId;
    private String teamDTOName;
    private String teamDTODescription;

    public TeamDTO(Integer teamDTOId, String teamDTOName, String teamDTODescription) {
        this.teamDTOId = teamDTOId;
        this.teamDTOName = teamDTOName;
        this.teamDTODescription = teamDTODescription;
    }

    public TeamDTO() {
    }

    public Integer getTeamDTOId() {
        return teamDTOId;
    }

    public void setTeamDTOId(Integer teamDTOId) {
        this.teamDTOId = teamDTOId;
    }

    public String getTeamDTOName() {
        return teamDTOName;
    }

    public void setTeamDTOName(String teamDTOName) {
        this.teamDTOName = teamDTOName;
    }

    public String getTeamDTODescription() {
        return teamDTODescription;
    }

    public void setTeamDTODescription(String teamDTODescription) {
        this.teamDTODescription = teamDTODescription;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "teamDTOId=" + teamDTOId +
                ", teamDTOName='" + teamDTOName + '\'' +
                ", teamDTODescription='" + teamDTODescription + '\'' +
                '}';
    }
}
