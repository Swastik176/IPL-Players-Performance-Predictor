package com.swastik.IplStatsAndPerformancePredictor.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BattingStatsDTO {
    private String playerName;
    private String teamName;
    private String teamCode;
    private String CountryName;
    private int matches;
    private int innings;
    private int runs;
    private String highestScore;
    private String average;
    private float strikeRate;
    private int fifties;
    private int centuries;
    private int fours;
    private int sixes;

}
