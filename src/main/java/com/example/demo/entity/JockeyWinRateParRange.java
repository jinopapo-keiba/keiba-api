package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JockeyWinRateParRange {
    String raceType;
    String range;
    JockeyWinRate jockeyWinRate;
}
