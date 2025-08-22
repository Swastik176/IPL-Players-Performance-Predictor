package com.swastik.IplStatsAndPerformancePredictor.repository;

import com.swastik.IplStatsAndPerformancePredictor.dto.BowlingStatsDTO;
import com.swastik.IplStatsAndPerformancePredictor.dto.CountryDTO;
import com.swastik.IplStatsAndPerformancePredictor.model.BowlingStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BowlingRepo extends JpaRepository<BowlingStats, Long> {

    @Query("SELECT new com.swastik.IplStatsAndPerformancePredictor.dto.BowlingStatsDTO(" +
            "pl.playerId, pl.name, " +
            "t.teamName, t.teamCode, " +
            "c.countryName, " +
            "bl.matches, bl.innings, bl.wickets, bl.overs, bl.economy, bl.foursConceded, bl.sixesConceded, bl.bowlingAvg, bl.bowlingStrikeRate, bl.fourWickets, bl.fiveWickets) " +
            "FROM Players pl " +
            "JOIN pl.bowlingStats bl " +
            "JOIN pl.team t " +
            "JOIN pl.country c " +
            "ORDER BY bl.wickets DESC"
    )
    List<BowlingStatsDTO> findAllPlayers();

    @Query("SELECT new com.swastik.IplStatsAndPerformancePredictor.dto.BowlingStatsDTO(" +
            "pl.playerId, pl.name, " +
            "t.teamName, t.teamCode, " +
            "c.countryName, " +
            "bl.matches, bl.innings, bl.wickets, bl.overs, bl.economy, bl.foursConceded, bl.sixesConceded, bl.bowlingAvg, bl.bowlingStrikeRate, bl.fourWickets, bl.fiveWickets) " +
            "FROM Players pl " +
            "JOIN pl.bowlingStats bl " +
            "JOIN pl.team t " +
            "JOIN pl.country c " +
            "WHERE t.teamId = :teamId " +
            "ORDER BY bl.wickets DESC"
    )
    List<BowlingStatsDTO> findAllPlayersByTeam(String teamId);

    @Query("SELECT new com.swastik.IplStatsAndPerformancePredictor.dto.BowlingStatsDTO(" +
            "pl.playerId, pl.name, " +
            "t.teamName, t.teamCode, " +
            "c.countryName, " +
            "bl.matches, bl.innings, bl.wickets, bl.overs, bl.economy, bl.foursConceded, bl.sixesConceded, bl.bowlingAvg, bl.bowlingStrikeRate, bl.fourWickets, bl.fiveWickets) " +
            "FROM Players pl " +
            "JOIN pl.bowlingStats bl " +
            "JOIN pl.team t " +
            "JOIN pl.country c " +
            "WHERE c.countryId = :countryId " +
            "ORDER BY bl.wickets DESC"
    )

    List<BowlingStatsDTO> findAllPlayersByCountry(String countryId);

    @Query("SELECT new com.swastik.IplStatsAndPerformancePredictor.dto.BowlingStatsDTO(" +
            "pl.playerId, pl.name, " +
            "t.teamName, t.teamCode, " +
            "c.countryName, " +
            "bl.matches, bl.innings, bl.wickets, bl.overs, bl.economy, bl.foursConceded, bl.sixesConceded, bl.bowlingAvg, bl.bowlingStrikeRate, bl.fourWickets, bl.fiveWickets) " +
            "FROM Players pl " +
            "JOIN pl.bowlingStats bl " +
            "JOIN pl.team t " +
            "JOIN pl.country c " +
            "WHERE pl.playerId = :playerId " +
            "ORDER BY bl.wickets DESC"
    )
    BowlingStatsDTO findPlayerById(String playerId);
}

