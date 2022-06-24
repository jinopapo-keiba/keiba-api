package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.RaceHorseResponse;
import com.example.demo.entity.RaceHorse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RaceHorseResponseConverter {
    private HorseResponseConverter horseResponseConverter;
    private JockeyResponseConverter jockeyResponseConverter;
    private ResultResponseConverter resultResponseConverter;

    public RaceHorseResponse convert(RaceHorse raceHorse){
        return RaceHorseResponse.builder()
                .weight(raceHorse.getWeight())
                .old(raceHorse.getOld())
                .frameNumber(raceHorse.getFrameNumber())
                .horse(horseResponseConverter.convert(raceHorse.getHorse()))
                .jockey(jockeyResponseConverter.convert(raceHorse.getJockey()))
                .raceResult(raceHorse.getRaceResult() == null ? null : resultResponseConverter.convert(raceHorse.getRaceResult()))
                .build();
    }
}
