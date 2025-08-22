package com.swastik.IplStatsAndPerformancePredictor.repository;

import com.swastik.IplStatsAndPerformancePredictor.dto.BattingStatsDTO;
import com.swastik.IplStatsAndPerformancePredictor.dto.CountryDTO;
import com.swastik.IplStatsAndPerformancePredictor.model.BattingStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BattingRepo extends JpaRepository<BattingStats, Long> {

    @Query("SELECT new com.swastik.IplStatsAndPerformancePredictor.dto.BattingStatsDTO( pl.playerId, pl.name, " +
            "t.teamName, t.teamCode, " +
            "c.countryName, " +
            "bt.matches, bt.innings, bt.runs, bt.highestScore, bt.average, bt.strikeRate, bt.fifties, bt.centuries, bt.fours, bt.sixes) " +
            "FROM Players pl " +
            "Join pl.battingStats bt " +
            "Join pl.team t " +
            "Join pl.country c " +
            "ORDER BY bt.runs DESC"
    )
    List<BattingStatsDTO> findAllPlayers();

    @Query("SELECT new com.swastik.IplStatsAndPerformancePredictor.dto.BattingStatsDTO( pl.playerId, pl.name, " +
            "t.teamName, t.teamCode, " +
            "c.countryName, " +
            "bt.matches, bt.innings, bt.runs, bt.highestScore, bt.average, bt.strikeRate, bt.fifties, bt.centuries, bt.fours, bt.sixes) " +
            "FROM Players pl " +
            "Join pl.battingStats bt " +
            "Join pl.team t " +
            "Join pl.country c " +
            " WHERE t.teamId = :teamId " +
            "ORDER BY bt.runs DESC"
    )
    List<BattingStatsDTO> findAllPlayersByTeam(String teamId);

    @Query("SELECT new com.swastik.IplStatsAndPerformancePredictor.dto.BattingStatsDTO( pl.playerId, pl.name, " +
            "t.teamName, t.teamCode, " +
            "c.countryName, " +
            "bt.matches, bt.innings, bt.runs, bt.highestScore, bt.average, bt.strikeRate, bt.fifties, bt.centuries, bt.fours, bt.sixes) " +
            "FROM Players pl " +
            "Join pl.battingStats bt " +
            "Join pl.team t " +
            "Join pl.country c " +
            " WHERE c.countryId = :countryId " +
            "ORDER BY bt.runs DESC"
    )
    List<BattingStatsDTO> findAllPlayersByCountry(String countryId);

    @Query("SELECT new com.swastik.IplStatsAndPerformancePredictor.dto.BattingStatsDTO( pl.playerId, pl.name, " +
            "t.teamName, t.teamCode, " +
            "c.countryName, " +
            "bt.matches, bt.innings, bt.runs, bt.highestScore, bt.average, bt.strikeRate, bt.fifties, bt.centuries, bt.fours, bt.sixes) " +
            "FROM Players pl " +
            "Join pl.battingStats bt " +
            "Join pl.team t " +
            "Join pl.country c " +
            " WHERE pl.playerId = :playerId " +
            "ORDER BY bt.runs DESC"
    )
    BattingStatsDTO findPlayerById(String playerId);
}
