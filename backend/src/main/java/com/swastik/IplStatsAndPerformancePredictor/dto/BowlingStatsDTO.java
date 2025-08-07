package com.swastik.IplStatsAndPerformancePredictor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BowlingStatsDTO {
    private String playerName;
    private String teamName;
    private String teamCode;
    private String countryName;
    private int matches;
    private int innings;
    private int wickets;
    private float overs;
    private float economy;
    private int foursConceded;
    private int sixesConceded;
    private float bowlingAvg;
    private float bowlingStrikeRate;
    private int fourWickets;
    private int fiveWickets;

}
