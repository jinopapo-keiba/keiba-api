package com.example.demo.repository.dto;

import com.example.demo.valueobject.RaceCondition;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class RaceQueryParam {
    private List<String> stadiums;
    private Integer round;
    private Date raceDate;
    private boolean beforeRace;
    private Date startRaceDate;
    private RaceCondition raceCondition;
    private List<Integer> horseIds;
    private Integer raceId;
    private Integer minRaceLength;
    private Integer maxRaceLength;
}
