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

    // Fetch All the countries
    public ResponseEntity<List<CountryDTO>> getAllCountry() {
        List<Country> countries = countryRepo.findAll();

        List<CountryDTO> countryDTOS = countries.stream()
                .map(country -> new CountryDTO(
                        country.getCountryId(),
                        country.getCountryName(),
                        country.getFlag()
                ))
                .collect(Collectors.toList());

        return new ResponseEntity<>(countryDTOS, HttpStatus.OK);
    }



    // Search Country
    public ResponseEntity<List<?>> searchCountry(String keyword) {
        List<Country> countries = countryRepo.findByKeyword(keyword);

        if(countries.isEmpty())
            return ResponseEntity.ok(new ArrayList<>());

        List<CountryDTO> countryDTOS = countries.stream()
                .map(country -> new CountryDTO(
                        country.getCountryId(),
                        country.getCountryName(),
                        country.getFlag()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(countryDTOS);
    }

    public ResponseEntity<List<?>> searchCountryById(String countryId) {
        Country country = countryRepo.findById(countryId).orElse(new Country());

        CountryDTO countryDTO = new CountryDTO(
                country.getCountryId(),
                country.getCountryId(),
                country.getFlag()
        );

        List<CountryDTO> countryDTOList = new ArrayList<>();

        countryDTOList.add(countryDTO);

        return ResponseEntity.ok(countryDTOList);
    }
}
