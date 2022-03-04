package com.example.demo.contoller.response;

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
    String raceDate;
    int id;
    int round;
    String stadium;
    List<RaceHorse> raceHorses;

    @Getter
    @Builder
    static public class RaceHorse{
        Integer weight;
        Integer old;
        Integer frameNumber;
        HorseResponse horse;
        Jockey jockey;
        RaceResult raceResult;
    }

    @Getter
    @Builder
    static public class Jockey{
        String name;
    }

    @Getter
    @Builder
    static public class RaceResult{
        long fullTime;
        int ranking;
        String cornerRanking;
        long lastRapTime;
    }
}
