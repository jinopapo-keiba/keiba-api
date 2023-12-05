package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RaceHorseResponse {
    Integer weight;
    Integer old;
    Integer frameNumber;
    Integer handicap;
    HorseResponse horse;
    JockeyResponse jockey;
    TrainerResponse trainer;
    ResultResponse raceResult;
}
