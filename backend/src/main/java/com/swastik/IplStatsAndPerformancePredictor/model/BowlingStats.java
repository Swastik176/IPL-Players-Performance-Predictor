package com.swastik.IplStatsAndPerformancePredictor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BowlingStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "playerId", unique = true)
    private Players players;

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
