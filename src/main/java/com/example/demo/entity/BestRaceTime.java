package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
public class BestRaceTime {
    Horse horse;
    Duration fullTime;
    Duration lastRapTime;
    String fullTimeStadium;
    Duration stadiumFullTime;
    Duration stadiumLastRapTime;
}
