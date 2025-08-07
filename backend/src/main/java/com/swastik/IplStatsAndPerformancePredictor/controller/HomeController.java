package com.swastik.IplStatsAndPerformancePredictor.controller;

import com.swastik.IplStatsAndPerformancePredictor.dto.CountryDTO;
import com.swastik.IplStatsAndPerformancePredictor.dto.IPLTeamDTO;
import com.swastik.IplStatsAndPerformancePredictor.service.HomeService;
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
    private HomeService homeService;

    // Fetch All the IPL teams
    @GetMapping("teams")
    public ResponseEntity<List<IPLTeamDTO>> getAllTeams(){
        return homeService.getAllTeams();
    }

    // Fetch All the Countries
    @GetMapping("countries")
    public ResponseEntity<List<CountryDTO>> getAllCountry(){
        return homeService.getAllCountry();
    }

    // Fetch All players according to role (batting/bowling)
    @GetMapping("players")
    public ResponseEntity<List<?>> getAllPlayers(@RequestParam String role){
        return homeService.getAllPlayers(role);
    }

    // Search players
    @GetMapping("search")
    public ResponseEntity<List<?>> searchPlayers(@RequestParam String keyword){
        return homeService.searchPlayers(keyword);
    }

    // Fetch Player by player id and role
    @GetMapping("player")
    public ResponseEntity<?> getPlayerById(@RequestParam String playerId, @RequestParam String role){
        return homeService.getPlayerById(playerId, role);
    }
}
