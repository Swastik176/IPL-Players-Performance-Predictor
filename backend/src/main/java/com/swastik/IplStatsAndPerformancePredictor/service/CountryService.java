package com.swastik.IplStatsAndPerformancePredictor.service;


import com.swastik.IplStatsAndPerformancePredictor.dto.CountryDTO;
import com.swastik.IplStatsAndPerformancePredictor.model.Country;
import com.swastik.IplStatsAndPerformancePredictor.repository.BattingRepo;
import com.swastik.IplStatsAndPerformancePredictor.repository.BowlingRepo;
import com.swastik.IplStatsAndPerformancePredictor.repository.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    @Autowired
    private BattingRepo battingRepo;

    @Autowired
    private BowlingRepo bowlingRepo;

    @Autowired
    CountryRepo countryRepo;

    // Fetch all the players of a country according to role(Batting/Bowling)
    public ResponseEntity<List<?>> getAllPlayersByCountry(String countryId, String role) {
        if(role.equalsIgnoreCase("batting"))
            return ResponseEntity.ok(battingRepo.findAllPlayersByCountry(countryId));

        else if(role.equalsIgnoreCase("bowling"))
            return ResponseEntity.ok(bowlingRepo.findAllPlayersByCountry(countryId));

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    // Search Country
    public ResponseEntity<List<?>> searchCountry(String keyword) {
        List<Country> countries = countryRepo.findByKeyword(keyword);

        if(countries.isEmpty())
            return ResponseEntity.notFound().build();

        List<CountryDTO> countryDTOS = countries.stream()
                .map(country -> new CountryDTO(
                        country.getCountryId(),
                        country.getCountryName(),
                        country.getFlag()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(countryDTOS);
    }
}
