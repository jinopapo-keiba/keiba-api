package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResultResponse {
    long fullTime;
    int ranking;
    String cornerRanking;
    int popular;
    float odds;
    long lastRapTime;
    float devFullTime;
    float devLastRapTime;
    float devTargetRaceFullTime;
    float devTargetRaceLastRapTime;
    float normalFullTime;
    float normalLastRapTime;
    float normalTargetRaceFullTime;
    float normalTargetRaceLastRapTime;
}
