package com.example.demo.repository.dto;

import com.example.demo.valueobject.RaceCondition;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class RaceQueryParam {
    private String stadium;
    private Integer round;
    private Date raceDate;
    private Boolean beforeRace;
    private Date startRaceDate;
    private RaceCondition raceCondition;
    private List<Integer> horseIds;
    private Integer raceId;
}
