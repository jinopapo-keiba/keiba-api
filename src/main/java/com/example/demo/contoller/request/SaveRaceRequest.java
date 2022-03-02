package com.example.demo.contoller.request;

import com.example.demo.entity.Jockey;
import com.example.demo.entity.RaceResult;
import lombok.Data;

import java.time.Duration;
import java.util.List;

@Data
public class SaveRaceRequest {
    String raceName;
    String raceType;
    Integer raceLength;
    String clockWise;
    String grade;
    String stadium;
    Integer round;
    String raceWeather;
    String raceCondition;
    String raceDate;
    List<RaceHorse> raceHorses;

    @Data
    static public class RaceHorse {
        Horse horse;
        Jockey jockey;
        Integer weight;
        Integer old;
        Integer frameNumber;
        RaceResult raceResult;
    }

    @Data
    static public class Horse {
        String name;
        String gender;
    }

    @Data
    static public class RaceResult{
        String fullTime;
        String rapRanking;
        Integer ranking;
        String lastRapTime;
        String cornerRanking;
    }
}
