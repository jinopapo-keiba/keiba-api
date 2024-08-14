package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.GetRaceResponse;
import com.example.demo.contoller.response.dto.PayoutResponse;
import com.example.demo.entity.*;
import com.example.demo.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetRaceResponseConverter {
    private RaceHorseResponseConverter raceHorseResponseConverter;
    private PayoutResponseConverter payoutResponseConverter;

    public GetRaceResponse convert(Race race){
        List<PayoutResponse> payouts = null;

        if (race.getPayouts() != null) {
            payouts = race.getPayouts().stream()
                    .map(payoutResponseConverter::convert)
                    .toList();
        }

        return GetRaceResponse.builder()
                .raceName(race.getRaceName())
                .raceType(race.getRaceType().getText())
                .raceLength(race.getRaceLength())
                .clockwise(race.getClockwise().getText())
                .raceWeather(race.getRaceWeather().getText())
                .grade(race.getGrade().getText())
                .oldLimit(race.getOldLimit().getText())
                .raceDate(DateUtils.getDateFormat().format(race.getRaceDate()))
                .id(race.getId())
                .round(race.getRound())
                .stadium(race.getStadium())
                .raceCondition(race.getRaceCondition().getText())
                .horseCount(race.getHorseCount())
                .raceHorses(race.getRaceHorses().stream()
                        .map(raceHorseResponseConverter::convert)
                        .collect(Collectors.toList())
                )
                .payouts(payouts)
                .build();
    }
}
