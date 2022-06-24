package com.example.demo.contoller.converter;


import com.example.demo.contoller.response.GetHorseRaceResultResponse;
import com.example.demo.entity.RaceHorse;
import com.example.demo.service.dto.RecentHorseResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetHorseRaceResultResponseConverter {
    private RaceResultResponseConverter raceResultResponseConverter;

    public GetHorseRaceResultResponse converter(RecentHorseResultDto recentHorseResultDto){
        return GetHorseRaceResultResponse.builder()
                .name(recentHorseResultDto.getRaceHorse().getHorse().getName())
                .id(recentHorseResultDto.getRaceHorse().getHorse().getId())
                .frameNumber(recentHorseResultDto.getRaceHorse().getFrameNumber())
                .raceResults(recentHorseResultDto.getRaces().stream()
                        .map((race -> raceResultResponseConverter.convert(
                                race,
                                race.getRaceHorses().stream()
                                        .filter(raceHorse -> raceHorse.getHorse().getId().equals(recentHorseResultDto.getRaceHorse().getHorse().getId()))
                                        .map(RaceHorse::getRaceResult)
                                        .findFirst()
                                        .get()
                        )))
                        .collect(Collectors.toList()))
                .build();
    }
}
