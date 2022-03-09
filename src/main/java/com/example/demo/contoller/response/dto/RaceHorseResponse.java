package com.example.demo.contoller.response.dto;

import com.example.demo.contoller.response.GetRaceResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RaceHorseResponse {
    Integer weight;
    Integer old;
    Integer frameNumber;
    HorseResponse horse;
    JockeyResponse jockey;
    RaceResultResponse raceResult;
}
