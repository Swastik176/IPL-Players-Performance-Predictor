package com.swastik.IplStatsAndPerformancePredictor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionRequestDTO {
    private String role;
    private BattingStatsDTO battingStatsDTO;
    private BowlingStatsDTO bowlingStatsDTO;
}
