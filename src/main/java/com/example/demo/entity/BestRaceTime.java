package com.example.demo.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BestRaceTime {
    RaceHorse raceHorse;
    Integer count;
    Float devBestFullTime;
    Float devAvgFullTime;
    Float devBestLastRapTime;
    Float devAvgLastRapTime;
    Float raceDevBestFullTime;
    Float raceDevAvgFullTime;
    Float raceDevBestLastRapTime;
    Float raceDevAvgLastRapTime;
}
