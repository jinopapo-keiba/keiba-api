package com.example.demo.contoller.request;

import lombok.Data;

@Data
public class SaveStadiumRequest {
    String stadium;
    Integer round;
    String raceDate;
    Integer stadiumDay;
    Integer stadiumRound;
}
