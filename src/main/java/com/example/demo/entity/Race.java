package com.example.demo.entity;

import com.example.demo.valueobject.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Race {
    String raceName;
    RaceType raceType;
    Integer raceLength;
    Clockwise clockwise;
    RaceCondition raceCondition;
    RaceWeather raceWeather;
    Grade grade;
    OldLimit oldLimit;
    Date raceDate;
    Integer id;
    Integer round;
    String stadium;
    List<RaceHorse> raceHorses;
    Integer horseCount;
    Integer stadiumDay;
}
