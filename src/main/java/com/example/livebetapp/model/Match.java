package com.example.livebetapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String league;
    private String homeTeam;
    private String awayTeam;

    private double homeWinOdds;
    private double drawOdds;
    private double awayWinOdds;

    private LocalDateTime matchStartTime;
}
