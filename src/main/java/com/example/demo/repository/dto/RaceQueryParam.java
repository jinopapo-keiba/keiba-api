package com.example.demo.repository.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RaceQueryParam {
    private String stadium;
    private int round;
    private Date raceDate;
}
