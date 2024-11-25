package com.example.demo.contoller.request;

import com.example.demo.entity.Jockey;
import com.example.demo.entity.Trainer;
import lombok.Data;

import java.util.List;

@Data
public class SaveRaceRequest {
    String raceName;
    String raceType;
    Integer raceLength;
    String clockwise;
    String grade;
    String oldLimit;
    String stadium;
    Integer round;
    String raceWeather;
    String raceCondition;
    String raceDate;
    Integer stadiumDay;
    Integer stadiumRound;
    List<RaceHorse> raceHorses;
    List<Payout> payouts;

    @Data
    static public class RaceHorse {
        Horse horse;
        Jockey jockey;
        Trainer trainer;
        Integer weight;
        Integer old;
        Integer frameNumber;
        Float handicap;
        RaceResult raceResult;
    }

    @Data
    static public class Horse {
        String name;
        String gender;
    }

    @Data
    static public class Payout {
        String betType;
        Float payout;
        String frameNumber;
    }

    @Data
    static public class RaceResult{
        String fullTime;
        Integer ranking;
        Integer popular;
        Float odds;
        String lastRapTime;
        String cornerRanking;
    }
}
