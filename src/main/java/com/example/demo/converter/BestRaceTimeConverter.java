package com.example.demo.converter;

import com.example.demo.entity.BestRaceTime;
import com.example.demo.service.dto.BestRaceTimeDto;
import org.springframework.stereotype.Component;

@Component
public class BestRaceTimeConverter {
    public BestRaceTime convert(BestRaceTimeDto bestRaceTimeDto,float devFullTime,float devLastRapTime){
        return BestRaceTime.builder()
                .raceHorse(bestRaceTimeDto.getRaceHorse())
                .fullTime(bestRaceTimeDto.getFullTime())
                .lastRapTime(bestRaceTimeDto.getLastRapTime())
                .count(bestRaceTimeDto.getCount())
                .devFullTime(devFullTime)
                .devLastRapTime(devLastRapTime)
                .build();
    }
}
