package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "レース馬レスポンス")
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
