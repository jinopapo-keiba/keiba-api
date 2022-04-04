package com.example.demo.entity;

import lombok.Builder;
import lombok.Value;

import java.time.Duration;

@Value
@Builder
public class BestRaceTime {
    RaceHorse raceHorse;
    Duration fullTime;
    Duration lastRapTime;
    Integer count;
    Float devFullTime;
    Float devLastRapTime;
}
