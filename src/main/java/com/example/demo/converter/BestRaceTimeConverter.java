package com.example.demo.converter;

import com.example.demo.entity.BestRaceTime;
import com.example.demo.entity.RaceHorse;
import org.springframework.stereotype.Component;

@Component
public class BestRaceTimeConverter {
    public BestRaceTime convert(RaceHorse raceHorse,int count,float devBestFullTime,float devAvgFullTime,float devBestLastRapTime,float devAvgLastRapTime){
        return BestRaceTime.builder()
                .raceHorse(raceHorse)
                .count(count)
                .devBestFullTime(devBestFullTime)
                .devAvgFullTime(devAvgFullTime)
                .devBestLastRapTime(devBestLastRapTime)
                .devAvgLastRapTime(devAvgLastRapTime)
                .build();
    }
}
