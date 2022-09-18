package com.example.demo.contoller.response;

import com.example.demo.contoller.response.dto.RaceHorseResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetBestRaceTimeResponse {
    List<BestRaceTime> bestRaceTimes;

    @Getter
    @Builder
    public static class BestRaceTime{
        RaceHorseResponse raceHorse;
        Long fullTime;
        Long lastRapTime;
        Integer count;
        Float devBestFullTime;
        Float devAvgFullTime;
        Float devBestLastRapTime;
        Float devAvgLastRapTime;
    }
}
