package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.GetRaceResponse;
import com.example.demo.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetRaceResponseConverter {
    private DateFormat dateFormat;
    private HorseResponseConverter horseResponseConverter;

    public GetRaceResponse convert(Race race){
        return GetRaceResponse.builder()
                .raceName(race.getRaceName())
                .raceType(race.getRaceType().getText())
                .raceLength(race.getRaceLength())
                .clockwise(race.getClockwise().getText())
                .raceWeather(race.getRaceWeather().getText())
                .grade(race.getGrade().getText())
                .raceDate(dateFormat.format(race.getRaceDate()))
                .id(race.getId())
                .round(race.getRound())
                .stadium(race.getStadium())
                .raceCondition(race.getRaceCondition().getText())
                .raceHorses(race.getRaceHorses().stream()
                        .map(this::raceHorseConvert)
                        .collect(Collectors.toList())
                )
                .build();
    }

    private GetRaceResponse.RaceHorse raceHorseConvert(RaceHorse raceHorse){
        return GetRaceResponse.RaceHorse.builder()
                .weight(raceHorse.getWeight())
                .old(raceHorse.getOld())
                .frameNumber(raceHorse.getFrameNumber())
                .horse(horseResponseConverter.convert(raceHorse.getHorse()))
                .jockey(this.jockeyConvert(raceHorse.getJockey()))
                .raceResult(raceHorse.getRaceResult() == null ? null : this.raceResultConvert(raceHorse.getRaceResult()))
                .build();
    }

    private GetRaceResponse.Jockey jockeyConvert(Jockey jockey){
        return GetRaceResponse.Jockey.builder()
                .name(jockey.getName())
                .build();
    }

    private GetRaceResponse.RaceResult raceResultConvert(RaceResult raceResult){
        return GetRaceResponse.RaceResult.builder()
                .fullTime(raceResult.getFullTime().toMillis())
                .ranking(raceResult.getRanking())
                .cornerRanking(raceResult.getCornerRanking())
                .lastRapTime(raceResult.getLastRapTime().toMillis())
                .build();
    }
}
