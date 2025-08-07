package com.swastik.IplStatsAndPerformancePredictor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryDTO {
    private String countryId;
    private String countryName;
    private String flagUrl;
}
