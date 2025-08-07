package com.swastik.IplStatsAndPerformancePredictor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Players {

    @Id
    private String playerId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private IPLTeams team;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;

    @OneToOne(mappedBy = "players", cascade = CascadeType.ALL, orphanRemoval = true)
    private BattingStats battingStats;

    @OneToOne(mappedBy = "players", cascade = CascadeType.ALL, orphanRemoval = true)
    private BowlingStats bowlingStats;
}
