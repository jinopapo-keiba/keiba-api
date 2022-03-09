package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
public class BestRaceTime {
    RaceHorse raceHorse;
    Duration fullTime;
    Duration lastRapTime;
}
