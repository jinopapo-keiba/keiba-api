package com.example.demo.entity;

import com.example.demo.converter.RaceConverter;
import com.example.demo.valueobject.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Date;
import java.util.List;

@Value
@Builder
@JsonDeserialize(using = RaceConverter.class)
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
    List<RaceHorse> raceHorses;
}
