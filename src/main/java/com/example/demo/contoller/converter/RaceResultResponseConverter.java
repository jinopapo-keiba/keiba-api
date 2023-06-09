package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.RaceResultResponse;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RaceResultResponseConverter {
    private final RaceHorseResponseConverter raceHorseResponseConverter;
    private final RaceResponseConverter raceResponseConverter;

    public RaceResultResponse convert(Race race, RaceHorse raceHorse){
        return RaceResultResponse.builder()
                .raceHorse(raceHorseResponseConverter.convert(raceHorse))
                .race(raceResponseConverter.convert(race))
                .build();
    }
}
