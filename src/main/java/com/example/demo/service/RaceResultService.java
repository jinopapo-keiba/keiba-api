package com.example.demo.service;

import com.example.demo.converter.BestRaceTimeConverter;
import com.example.demo.converter.HorseResultConverter;
import com.example.demo.entity.BestRaceTime;
import com.example.demo.entity.HorseResult;
import com.example.demo.entity.RaceResultSummary;
import com.example.demo.entity.StadiumTime;
import com.example.demo.repository.RaceResultRepository;
import com.example.demo.service.dto.BestRaceTimeDto;
import com.example.demo.service.dto.HorseResultDto;
import com.example.demo.valueobject.Grade;
import com.example.demo.valueobject.RaceCondition;
import com.example.demo.valueobject.SummaryType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RaceResultService {
    private final RaceResultRepository raceResultRepository;
    private final BestRaceTimeConverter bestRaceTimeConverter;
    private final HorseResultConverter horseResultConverter;

    public List<BestRaceTime> fetchBestRaceTime(String stadium, Integer raceLength, String raceId,
                                                SummaryType summaryType, RaceCondition raceCondition){
        List<BestRaceTimeDto> bestRaceTimeDtos = raceResultRepository.fetchBestRaceTimes(stadium,raceLength,raceId,
                summaryType, raceCondition);
        RaceResultSummary raceResultSummaries = raceResultRepository.fetchRaceResultSummary(
                stadium,raceLength,raceCondition,null,raceId);
        List<BestRaceTime> bestRaceTimes = new ArrayList<>();
        bestRaceTimeDtos.forEach(
                bestRaceTimeDto -> {
                    if(raceResultSummaries == null) {
                        bestRaceTimes.add(
                                bestRaceTimeConverter.convert(
                                        bestRaceTimeDto,
                                        0,
                                        0
                                )
                        );
                        return;
                    }

                    float devFullTime = calcDev(
                            bestRaceTimeDto.getFullTime().toMillis(),
                            raceResultSummaries.getMeanFullTime(),
                            raceResultSummaries.getStdevpFullTime()
                    );
                    float devLastRapTime = calcDev(
                            bestRaceTimeDto.getLastRapTime().toMillis(),
                            raceResultSummaries.getMeanLastRapTime(),
                            raceResultSummaries.getStdevpLastRapTime()
                    );
                    bestRaceTimes.add(
                            bestRaceTimeConverter.convert(
                                    bestRaceTimeDto,
                                    devFullTime,
                                    devLastRapTime
                            )
                    );
                }
        );
        return bestRaceTimes;
    }

    public List<HorseResult> fetchHorseResult(Integer horseId) {
        List<HorseResultDto> horseResultDtos = raceResultRepository.fetchHorseResult(horseId);
        return horseResultDtos.stream().map(
                (horseResultDto) -> {
                    float devFullTime = calcDev(
                            horseResultDto.getRaceResult().getFullTime().toMillis(),
                            horseResultDto.getMeanFullTime(),
                            horseResultDto.getStdevpFullTime()
                    );
                    float devLastRapTime = calcDev(
                            horseResultDto.getRaceResult().getLastRapTime().toMillis(),
                            horseResultDto.getMeanLastRapTime(),
                            horseResultDto.getStdevpLastRapTime()
                    );
                    return horseResultConverter.converter(horseResultDto,devFullTime,devLastRapTime);
                }
        ).collect(Collectors.toList());
    }

    private float calcDev(long time,long ave, float stdevp) {
        if(time == 0) {
            return 0;
        }
        return (((ave - time) * 10) / stdevp) + 50;
    }

    public List<StadiumTime> fetchStadiumSummaryTimes(String time,Integer raceLength, Grade grade) {
       return raceResultRepository.fetchStadiumSummaryTimes(time,raceLength,grade);
    }
}
