package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JockeyWinRateParStadium {
    String raceType;
    String stadium;
    JockeyWinRate jockeyWinRate;
}
