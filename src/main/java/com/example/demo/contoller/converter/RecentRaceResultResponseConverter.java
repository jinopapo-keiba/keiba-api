package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.RecentRaceResultResponse;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecentRaceResultResponseConverter {
    private final RaceHorseResponseConverter raceHorseResponseConverter;
    private final RaceResponseConverter raceResponseConverter;
    private final CornerRankingResponseConverter cornerRankingResponseConverter;

    public RecentRaceResultResponse convert(Race race, RaceHorse raceHorse){
        return RecentRaceResultResponse.builder()
                .raceHorse(raceHorseResponseConverter.convert(raceHorse))
                .race(raceResponseConverter.convert(race))
                .cornerRankings(cornerRankingResponseConverter.convertAllHorses(race))
                .build();
    }
}
