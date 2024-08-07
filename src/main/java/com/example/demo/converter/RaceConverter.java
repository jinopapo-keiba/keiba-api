package com.example.demo.converter;

import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.entity.Race;
import com.example.demo.utils.DateUtils;
import com.example.demo.valueobject.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RaceConverter {
    private RaceHorseConverter raceHorseConverter;

    @SneakyThrows
    public Race converter(SaveRaceRequest saveRaceRequest) {
        return Race.builder()
                .raceName(saveRaceRequest.getRaceName())
                .raceType(RaceType.toEnum(saveRaceRequest.getRaceType()))
                .raceLength(saveRaceRequest.getRaceLength())
                .clockwise(Clockwise.toEnum(saveRaceRequest.getClockwise()))
                .grade(Grade.toEnum(saveRaceRequest.getGrade()))
                .oldLimit(OldLimit.toEnum(saveRaceRequest.getOldLimit()))
                .raceWeather(RaceWeather.toEnum(saveRaceRequest.getRaceWeather()))
                .raceCondition(RaceCondition.toEnum(saveRaceRequest.getRaceCondition()))
                .raceDate(DateUtils.getDateFormat().parse(saveRaceRequest.getRaceDate()))
                .round(saveRaceRequest.getRound())
                .stadium(saveRaceRequest.getStadium())
                .raceHorses(
                        saveRaceRequest.getRaceHorses().stream()
                                .map(raceHorseConverter::convert)
                                .collect(Collectors.toList()))
                .stadiumDay(saveRaceRequest.getStadiumDay())
                .build();
    }
}
