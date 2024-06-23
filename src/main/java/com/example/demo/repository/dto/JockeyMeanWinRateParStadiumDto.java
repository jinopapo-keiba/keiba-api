package com.example.demo.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JockeyMeanWinRateParStadiumDto {
    String raceType;
    String stadium;
    float avg;
    float stddevd;
}
