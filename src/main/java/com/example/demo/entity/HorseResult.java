package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HorseResult {
    float devLastRapTime;
    float devFullTime;
    RaceResult raceResult;
    Race race;
}
