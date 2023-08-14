package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.RaceResponse;
import com.example.demo.entity.Race;
import com.example.demo.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;

@AllArgsConstructor
@Component
public class RaceResponseConverter {

    public RaceResponse convert(Race race) {
        return RaceResponse.builder()
                .id(race.getId())
                .grade(race.getGrade().getText())
                .oldLimit(race.getOldLimit().getText())
                .raceLength(race.getRaceLength())
                .clockwise(race.getClockwise().getText())
                .raceCondition(race.getRaceCondition().getText())
                .raceDate(DateUtils.getDateFormat().format(race.getRaceDate()))
                .round(race.getRound())
                .stadium(race.getStadium())
                .raceName(race.getRaceName())
                .raceType(race.getRaceType().getText())
                .build();
    }
}
