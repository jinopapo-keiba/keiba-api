package com.example.demo.entity;

import lombok.Builder;
import lombok.Value;

import java.time.Duration;

@Value
@Builder
public class RaceResult {
    Duration fullTime;
    String rapRanking;
    Integer ranking;
    String cornerRanking;
    Duration lastRapTime;
}
