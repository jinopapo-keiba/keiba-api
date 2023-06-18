package com.example.demo.contoller.response;

import com.example.demo.contoller.response.dto.RaceHorseResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetRaceResponse {
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
    List<RaceHorseResponse> raceHorses;
}
