package com.swastik.IplStatsAndPerformancePredictor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IPLTeams {

    @Id
    private String teamId;
    private String teamCode;
    private String teamName;
    private String teamLogoUrl;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Players> playersList;
}
