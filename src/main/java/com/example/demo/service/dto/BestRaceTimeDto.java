package com.example.demo.service.dto;

import com.example.demo.entity.RaceHorse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
public class BestRaceTimeDto {
    RaceHorse raceHorse;
    Duration fullTime;
    Duration lastRapTime;
    Integer count;
}
