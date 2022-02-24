package com.example.demo.entity;

import com.example.demo.converter.RaceConverter;
import com.example.demo.valueobject.Clockwise;
import com.example.demo.valueobject.Grad;
import com.example.demo.valueobject.RaceCondition;
import com.example.demo.valueobject.RaceType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Date;

@Value
@Builder
@JsonDeserialize(using = RaceConverter.class)
public class Race {
    String raceName;
    RaceType raceType;
    Integer raceLenght;
    Clockwise clockwise;
    RaceCondition raceCondition;
    Grad grad;
    Date raceDate;
    Integer id;
}
