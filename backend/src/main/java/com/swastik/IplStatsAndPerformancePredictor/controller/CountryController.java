package com.swastik.IplStatsAndPerformancePredictor.controller;

import com.swastik.IplStatsAndPerformancePredictor.service.CountryService;
import com.swastik.IplStatsAndPerformancePredictor.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private PlayerService playerService;

    // Fetch all the players of a country according to role(Batting/Bowling)
    @GetMapping("players")
    public ResponseEntity<List<?>> getAllPlayersByCountry(@RequestParam String countryId, @RequestParam String role){
        return playerService.getAllPlayersByCountry(countryId, role);
    }

    // Search country
    @GetMapping("search")
    public ResponseEntity<List<?>> searchCountry(@RequestParam String keyword){
        return countryService.searchCountry(keyword);
    }

    // Fetch country by countryId
    @GetMapping("{countryId}")
    public ResponseEntity<List<?>> searchCountryById(@PathVariable String countryId){
        return countryService.searchCountryById(countryId);
    }
}
