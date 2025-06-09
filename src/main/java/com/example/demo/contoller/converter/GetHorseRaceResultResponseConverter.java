package com.example.demo.contoller.converter;


import com.example.demo.contoller.response.GetHorseRaceResultResponse;
import com.example.demo.service.dto.RecentHorseResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetHorseRaceResultResponseConverter {
    private RecentRaceResultResponseConverter recentRaceResultResponseConverter;

    public GetHorseRaceResultResponse converter(RecentHorseResultDto recentHorseResultDto){

        return GetHorseRaceResultResponse.builder()
                .name(recentHorseResultDto.getRaceHorse().getHorse().getName())
                .id(recentHorseResultDto.getRaceHorse().getHorse().getId())
                .handicap(recentHorseResultDto.getRaceHorse().getHandicap())
                .frameNumber(recentHorseResultDto.getRaceHorse().getFrameNumber())
                .raceResults(recentHorseResultDto.getRaces().stream()
                        .map((race -> recentRaceResultResponseConverter.convert(
                                race,
                                race.getRaceHorses().stream()
                                        .filter(raceHorse -> raceHorse.getHorse().getId().equals(recentHorseResultDto.getRaceHorse().getHorse().getId()))
                                        .findFirst()
                                        .get()
                        )))
                        .collect(Collectors.toList()))
                .build();
    }
}
