package com.swastik.IplStatsAndPerformancePredictor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattingStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "playerId", unique = true)
    private Players players;

    private int matches;
    private int innings;
    private int runs;
    private String highestScore;
    private String average; // Using String for '-'
    private float strikeRate;
    private int centuries;
    private int fifties;
    private int fours;
    private int sixes;
}
