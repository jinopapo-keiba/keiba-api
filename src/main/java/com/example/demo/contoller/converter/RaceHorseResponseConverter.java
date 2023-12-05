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
    private TrainerResponseConverter trainerResponseConverter;

    public RaceHorseResponse convert(RaceHorse raceHorse){
        return RaceHorseResponse.builder()
                .weight(raceHorse.getWeight())
                .old(raceHorse.getOld())
                .frameNumber(raceHorse.getFrameNumber())
                .handicap(raceHorse.getHandicap())
                .horse(horseResponseConverter.convert(raceHorse.getHorse()))
                .jockey(jockeyResponseConverter.convert(raceHorse.getJockey()))
                .trainer(trainerResponseConverter.convert(raceHorse.getTrainer()))
                .raceResult(raceHorse.getRaceResult() == null ? null : resultResponseConverter.convert(raceHorse.getRaceResult()))
                .build();
    }
}
