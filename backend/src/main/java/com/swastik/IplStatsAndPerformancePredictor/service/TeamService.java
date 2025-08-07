package com.swastik.IplStatsAndPerformancePredictor.service;

import com.swastik.IplStatsAndPerformancePredictor.dto.IPLTeamDTO;
import com.swastik.IplStatsAndPerformancePredictor.model.IPLTeams;
import com.swastik.IplStatsAndPerformancePredictor.repository.BattingRepo;
import com.swastik.IplStatsAndPerformancePredictor.repository.BowlingRepo;
import com.swastik.IplStatsAndPerformancePredictor.repository.IPLTeamRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private BattingRepo battingRepo;

    @Autowired
    private BowlingRepo bowlingRepo;

    @Autowired
    private IPLTeamRepo iplTeamRepo;

    // Fetch all the players of a team according to role(Batting/Bowling)
    public ResponseEntity<List<?>> getAllPlayersByTeam(String teamId, String role) {
        if(role.equalsIgnoreCase("batting"))
            return ResponseEntity.ok(battingRepo.findAllPlayersByTeam(teamId));

        else if(role.equalsIgnoreCase("bowling"))
            return ResponseEntity.ok(bowlingRepo.findAllPlayersByTeam(teamId));

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<?>> searchTeam(String keyword) {
        List<IPLTeams> teams = iplTeamRepo.findByKeyword(keyword);

        if(teams.isEmpty())
            return ResponseEntity.notFound().build();

        List<IPLTeamDTO> teamDTOS = teams.stream()
                .map(team -> new IPLTeamDTO(
                        team.getTeamId(),
                        team.getTeamName(),
                        team.getTeamCode(),
                        team.getTeamLogoUrl()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(teamDTOS);
    }
}
