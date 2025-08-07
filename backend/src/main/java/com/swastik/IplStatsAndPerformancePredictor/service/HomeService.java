package com.swastik.IplStatsAndPerformancePredictor.service;

import com.swastik.IplStatsAndPerformancePredictor.dto.CountryDTO;
import com.swastik.IplStatsAndPerformancePredictor.dto.IPLTeamDTO;
import com.swastik.IplStatsAndPerformancePredictor.dto.PlayersDTO;
import com.swastik.IplStatsAndPerformancePredictor.model.Country;
import com.swastik.IplStatsAndPerformancePredictor.model.IPLTeams;
import com.swastik.IplStatsAndPerformancePredictor.model.Players;
import com.swastik.IplStatsAndPerformancePredictor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    @Autowired
    BattingRepo battingRepo;

    @Autowired
    BowlingRepo bowlingRepo;

    @Autowired
    CountryRepo countryRepo;

    @Autowired
    IPLTeamRepo iplTeamRepo;

    @Autowired
    PlayersRepo playersRepo;

    // Fetch All players according to role (Batting/Bowling)
    public ResponseEntity<List<?>>getAllPlayers(String role) {
        if(role.equalsIgnoreCase("batting"))
            return new ResponseEntity<>(battingRepo.findAllPlayers(), HttpStatus.OK);

        else if(role.equalsIgnoreCase("bowling"))
            return new ResponseEntity<>(bowlingRepo.findAllPlayers(), HttpStatus.OK);

        return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

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

    // Fetch All the IPL teams
    public ResponseEntity<List<IPLTeamDTO>> getAllTeams() {
        List<IPLTeams> teams = iplTeamRepo.findAll();

        List<IPLTeamDTO> teamDTOs = teams.stream()
                .map(team -> new IPLTeamDTO(
                        team.getTeamId(),
                        team.getTeamName(),
                        team.getTeamCode(),
                        team.getTeamLogoUrl()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(teamDTOs);
    }

    // Search Players
    public ResponseEntity<List<?>> searchPlayers(String keyword) {
        List<Players> players = playersRepo.findByKeyword(keyword);

        if(players.isEmpty())
            return ResponseEntity.notFound().build();

        List<PlayersDTO> playersDTOS = players.stream()
                .map(player -> new PlayersDTO(
                        player.getPlayerId(),
                        player.getName()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(playersDTOS);
    }

    // Fetch player by player id
    public ResponseEntity<?> getPlayerById(String playerId, String role) {

        if(role.equalsIgnoreCase("batting"))
            return ResponseEntity.ok(battingRepo.findPlayerById(playerId));

        else if(role.equalsIgnoreCase("bowling"))
            return ResponseEntity.ok(bowlingRepo.findPlayerById(playerId));

        return ResponseEntity.notFound().build();
    }
}
