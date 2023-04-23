package com.example.demo.service.dto;

import com.example.demo.valueobject.RaceCondition;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class RecentRaceQuery {
    Integer raceId;
    RaceCondition raceCondition;
    List<String> stadiums;
    Integer minRaceLength;
    Integer maxRaceLength;
}
