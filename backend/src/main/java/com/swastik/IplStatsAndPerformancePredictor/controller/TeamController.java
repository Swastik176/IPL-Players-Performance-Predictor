package com.swastik.IplStatsAndPerformancePredictor.controller;

import com.swastik.IplStatsAndPerformancePredictor.service.PlayerService;
import com.swastik.IplStatsAndPerformancePredictor.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    // Fetch all the players of the team according to role (Batting/Bowling)
    @GetMapping("players")
    public ResponseEntity<List<?>> getAllPlayersByTeam(@RequestParam String teamId, @RequestParam String role){
        return playerService.getAllPlayersByTeam(teamId, role);
    }

    // Search Teams
    @GetMapping("search")
    public ResponseEntity<List<?>> searchTeams(@RequestParam String keyword){
        return teamService.searchTeam(keyword);
    }

    // Fetch Team by teamId
    @GetMapping("{teamId}")
    public ResponseEntity<List<?>> searchTeamById(@PathVariable String teamId){ return teamService.searchTeamById(teamId);}
}
