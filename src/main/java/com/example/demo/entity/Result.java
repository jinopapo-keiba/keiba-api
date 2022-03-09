package com.example.demo.entity;

import com.example.demo.valueobject.Grade;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Result {
    Date raceDate;
    Grade grade;
    Integer old;
    Integer weight;
    RaceResult raceResult;
}
