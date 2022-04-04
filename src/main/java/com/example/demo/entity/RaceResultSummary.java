package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RaceResultSummary {
    Integer meanFullTime;
    Integer meanLastRapTime;
    Integer stdevpFullTime;
    Integer stdevpLastRapTime;
}
