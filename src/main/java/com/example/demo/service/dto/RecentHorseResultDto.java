package com.example.demo.service.dto;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class RecentHorseResultDto {
    private RaceHorse raceHorse;
    private List<Race> races;
}
