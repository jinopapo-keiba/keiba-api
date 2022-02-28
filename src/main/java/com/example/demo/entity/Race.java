package com.example.demo.entity;

import com.example.demo.valueobject.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class Race {
    String raceName;
    RaceType raceType;
    Integer raceLength;
    Clockwise clockwise;
    RaceCondition raceCondition;
    RaceWeather raceWeather;
    Grade grade;
    Date raceDate;
    Integer id;
    Integer round;
    String stadium;
    List<RaceHorse> raceHorses;
}
