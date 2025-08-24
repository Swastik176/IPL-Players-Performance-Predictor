package com.swastik.IplStatsAndPerformancePredictor.controller;

import com.swastik.IplStatsAndPerformancePredictor.dto.CountryDTO;
import com.swastik.IplStatsAndPerformancePredictor.dto.IPLTeamDTO;
import com.swastik.IplStatsAndPerformancePredictor.service.CountryService;
import com.swastik.IplStatsAndPerformancePredictor.service.PlayerService;
import com.swastik.IplStatsAndPerformancePredictor.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Handles all the api calls from the Home page and All Players page
 * Fetching All IPL Teams
 * Fetching All Countries
 * Fetching All the Players according to role (Batting/Bowling)
 */
@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private TeamService teamService;

    // Fetch All the IPL teams
    @GetMapping("teams")
    public ResponseEntity<List<IPLTeamDTO>> getAllTeams(){
        return teamService.getAllTeams();
    }

    // Fetch All the Countries
    @GetMapping("countries")
    public ResponseEntity<List<CountryDTO>> getAllCountry(){
        return countryService.getAllCountry();
    }

    // Fetch All players according to role (batting/bowling)
    @GetMapping("players")
    public ResponseEntity<List<?>> getAllPlayers(@RequestParam String role){
        return playerService.getAllPlayers(role);
    }

    // Search players
    @GetMapping("/player/search")
    public ResponseEntity<List<?>> searchPlayers(@RequestParam String keyword){
        return playerService.searchPlayers(keyword);
    }

    // Fetch Player by player id and role
    @GetMapping("player")
    public ResponseEntity<?> getPlayerById(@RequestParam String playerId, @RequestParam String role){
        return playerService.getPlayerById(playerId, role);
    }
}
