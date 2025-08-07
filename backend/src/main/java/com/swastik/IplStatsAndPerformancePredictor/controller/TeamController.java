package com.swastik.IplStatsAndPerformancePredictor.controller;

import com.swastik.IplStatsAndPerformancePredictor.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Fetch all the players of the team according to role (Batting/Bowling)
    @GetMapping("players")
    public ResponseEntity<List<?>> getAllPlayersByTeam(@RequestParam String teamId, @RequestParam String role){
        return teamService.getAllPlayersByTeam(teamId, role);
    }

    // Search Teams
    @GetMapping("search")
    public ResponseEntity<List<?>> searchTeams(@RequestParam String keyword){
        return teamService.searchTeam(keyword);
    }
}
