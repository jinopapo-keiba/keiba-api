package com.example.demo.converter;

import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.entity.RaceHorse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RaceHorseConverter {
    private HorseConverter horseConverter;
    private RaceResultConverter raceResultConverter;

    public RaceHorse convert(SaveRaceRequest.RaceHorse raceHorse){
        return RaceHorse.builder()
                .horse(horseConverter.convert(raceHorse.getHorse()))
                .jockey(raceHorse.getJockey())
                .trainer(raceHorse.getTrainer())
                .frameNumber(raceHorse.getFrameNumber())
                .old(raceHorse.getOld())
                .handicap(raceHorse.getHandicap().intValue())
                .weight(raceHorse.getWeight())
                .raceResult(raceHorse.getRaceResult() == null ? null : raceResultConverter.converter(raceHorse.getRaceResult()))
                .build();
    }
}
