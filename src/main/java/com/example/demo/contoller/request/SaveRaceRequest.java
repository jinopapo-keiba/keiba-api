package com.example.demo.contoller.request;

import lombok.Data;

@Data
public class SaveRaceRequest {
    String raceName;
    String raceType;
    int raceLength;
    String clockWise;
    String grade;
    String raceDate;
}
