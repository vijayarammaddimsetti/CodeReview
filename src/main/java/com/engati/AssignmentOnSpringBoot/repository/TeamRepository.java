package com.engati.AssignmentOnSpringBoot.repository;

import com.engati.AssignmentOnSpringBoot.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    boolean existsByTeamName(String teamDTOName);
}
