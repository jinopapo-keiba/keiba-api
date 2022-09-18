package com.example.demo.service;

import com.example.demo.converter.BestRaceTimeConverter;
import com.example.demo.converter.HorseResultConverter;
import com.example.demo.entity.*;
import com.example.demo.repository.RaceRepository;
import com.example.demo.repository.RaceResultRepository;
import com.example.demo.repository.dto.RaceQueryParam;
import com.example.demo.service.dto.HorseResultDto;
import com.example.demo.service.dto.RecentHorseResultDto;
import com.example.demo.utils.DateUtils;
import com.example.demo.valueobject.Grade;
import com.example.demo.valueobject.RaceCondition;
import com.example.demo.valueobject.SummaryType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RaceResultService {
    private final RaceResultRepository raceResultRepository;
    private final BestRaceTimeConverter bestRaceTimeConverter;
    private final HorseResultConverter horseResultConverter;
    private final RaceRepository raceRepository;

    public List<BestRaceTime> fetchBestRaceTime(String stadium, Integer raceLength, Integer raceId,
                                                RaceCondition raceCondition){
        Race targeRace = raceRepository.fetchRace(RaceQueryParam.builder()
                .raceId(raceId)
                .beforeRace(true)
                .build()).get(0);
        List<Race> recentRanRaces = raceRepository.fetchRace(
                RaceQueryParam.builder()
                        .horseIds(targeRace.getRaceHorses().stream()
                                .map(raceHorse -> raceHorse.getHorse().getId())
                                .collect(Collectors.toList()))
                        .raceCondition(raceCondition)
                        .startRaceDate(DateUtils.convertLocalDateTime2Date(LocalDateTime.now().minusYears(2)))
                        .stadium(stadium)
                        .raceLength(raceLength)
                        .build()
        );
        List<RecentHorseResultDto> targetRaceHorses = targeRace.getRaceHorses().stream()
                .map(
                        raceHorse -> RecentHorseResultDto.builder()
                                .raceHorse(raceHorse)
                                .races(recentRanRaces.stream()
                                        .filter(
                                                race -> race.getRaceHorses().stream()
                                                        .anyMatch(horse -> Objects.equals(raceHorse.getHorse().getId(),horse.getHorse().getId()))
                                        )
                                        .collect(Collectors.toList()))
                                .build())
                .collect(Collectors.toList());

        List<BestRaceTime> bestRaceTimes = new ArrayList<>();
        targetRaceHorses.forEach(
                targetRaceHorse -> {
                    if(targetRaceHorse.getRaces().isEmpty()){
                        bestRaceTimes.add(bestRaceTimeConverter.convert(
                            targetRaceHorse.getRaceHorse(),
                                0,
                                0,
                                0,
                                0,
                                0
                        ));
                        return;
                    }
                    List<RaceResult> results = targetRaceHorse.getRaces().stream()
                            .map(
                                    race -> race.getRaceHorses().stream()
                                            .filter(raceHorse -> raceHorse.getHorse().getId().equals(targetRaceHorse.getRaceHorse().getHorse().getId()))
                                            .map(RaceHorse::getRaceResult)
                                            .findFirst()
                                            .get())
                            .collect(Collectors.toList());

                    List<Float> devFullTimes = results.stream()
                            .map(RaceResult::calcDevFullTime)
                            .collect(Collectors.toList());
                    Float bestFullTime = devFullTimes.stream()
                            .max(Float::compareTo).get();
                    float avgFullTime = devFullTimes.stream()
                            .reduce(Float::sum).get()/devFullTimes.size();
                    List<Float> devLastRapTimes = results.stream()
                            .map(RaceResult::calcDevLastRapTime)
                            .collect(Collectors.toList());
                    Float bestLastRapTime = devLastRapTimes.stream()
                            .max(Float::compareTo).get();
                    float avgLastRapTime = devLastRapTimes.stream()
                            .reduce(Float::sum).get()/devLastRapTimes.size();

                    bestRaceTimes.add(bestRaceTimeConverter.convert(
                            targetRaceHorse.getRaceHorse(),
                            results.size(),
                            bestFullTime,
                            avgFullTime,
                            bestLastRapTime,
                            avgLastRapTime));
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
