package com.swastik.IplStatsAndPerformancePredictor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IPLTeamDTO {
    private String teamId;
    private String teamName;
    private String teamCode;
    private String logoUrl;
}
