package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResultResponse {
    long fullTime;
    int ranking;
    String cornerRanking;
    long lastRapTime;
    float devFullTime;
    float devLastRapTime;
    float devTargetRaceFullTime;
    float devTargetRaceLastRapTime;
}
