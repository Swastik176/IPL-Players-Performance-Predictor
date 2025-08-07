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
public class Country {

    @Id
    private String countryId;
    private String countryName;
    private String flag;

    @OneToMany(mappedBy="country", cascade = CascadeType.ALL)
    private List<Players> playersList;
}
