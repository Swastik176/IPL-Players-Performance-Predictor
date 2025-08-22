package com.swastik.IplStatsAndPerformancePredictor.service;

import com.swastik.IplStatsAndPerformancePredictor.dto.PlayersDTO;
import com.swastik.IplStatsAndPerformancePredictor.model.Players;
import com.swastik.IplStatsAndPerformancePredictor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

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

    // Fetch all the players of a country according to role(Batting/Bowling)
    public ResponseEntity<List<?>> getAllPlayersByCountry(String countryId, String role) {
        if(role.equalsIgnoreCase("batting"))
            return ResponseEntity.ok(battingRepo.findAllPlayersByCountry(countryId));

        else if(role.equalsIgnoreCase("bowling"))
            return ResponseEntity.ok(bowlingRepo.findAllPlayersByCountry(countryId));

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    // Fetch all the players of a team according to role(Batting/Bowling)
    public ResponseEntity<List<?>> getAllPlayersByTeam(String teamId, String role) {
        if(role.equalsIgnoreCase("batting"))
            return ResponseEntity.ok(battingRepo.findAllPlayersByTeam(teamId));

        else if(role.equalsIgnoreCase("bowling"))
            return ResponseEntity.ok(bowlingRepo.findAllPlayersByTeam(teamId));

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
