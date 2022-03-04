package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.GetBestRaceTimeResponse;
import com.example.demo.entity.BestRaceTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class GetBestRaceTimeResponseConverter {
    private HorseResponseConverter horseResponseConverter;

    public GetBestRaceTimeResponse converter(List<BestRaceTime> bestRaceTimes){
        return GetBestRaceTimeResponse.builder()
                .bestRaceTimes(
                        bestRaceTimes.stream()
                                .map(bestRaceTime -> GetBestRaceTimeResponse.BestRaceTime.builder()
                                        .horse(horseResponseConverter.convert(bestRaceTime.getHorse()))
                                        .lastRapTime(bestRaceTime.getLastRapTime().toMillis())
                                        .fullTime(bestRaceTime.getFullTime().toMillis())
                                        .stadiumLastRapTime(bestRaceTime.getStadiumLastRapTime().toMillis())
                                        .stadiumFullTime(bestRaceTime.getStadiumFullTime().toMillis())
                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }
}
