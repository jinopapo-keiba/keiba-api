package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "レースレスポンス")
public class RaceResponse {
    String raceName;
    String raceType;
    int raceLength;
    String clockwise;
    String raceCondition;
    String raceWeather;
    String grade;
    String oldLimit;
    String raceDate;
    int id;
    int round;
    String stadium;
    int horseCount;
    int stadiumDay;
    int stadiumRound;
}
