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
    private RaceHorseResponseConverter raceHorseResponseConverter;

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
                        .map(raceHorseResponseConverter::convert)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
