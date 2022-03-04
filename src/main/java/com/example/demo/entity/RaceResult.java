package com.example.demo.entity;

import lombok.*;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceResult {
    Duration fullTime;
    Integer ranking;
    String cornerRanking;
    Duration lastRapTime;
}
