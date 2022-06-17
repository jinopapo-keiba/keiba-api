package com.example.demo.service.dto;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HorseResultDto {
    Integer meanFullTime;
    Integer meanLastRapTime;
    Integer stdevpFullTime;
    Integer stdevpLastRapTime;
    RaceResult raceResult;
    Race race;

}
