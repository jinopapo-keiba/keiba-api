package com.example.demo.contoller.response;

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
        HorseResponse horse;
        Long fullTime;
        Long lastRapTime;
        Long stadiumFullTime;
        Long stadiumLastRapTime;
        String fullTimeStadium;
    }
}
