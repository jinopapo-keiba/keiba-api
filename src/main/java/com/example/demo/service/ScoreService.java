package com.example.demo.service;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import com.example.demo.entity.HorseScore;
import com.example.demo.service.dto.RecentHorseResultDto;
import com.example.demo.service.dto.RecentRaceQuery;
import com.example.demo.valueobject.Grade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreService {
    private final RaceService raceService;

    public List<HorseScore> calcScore(int id) {
        Race targetRace = raceService.fetchRace(id).get(0);
        List<String> targetStadium = new ArrayList<>();
        if(targetRace.getGrade() != Grade.NONE) {
            targetStadium = Arrays.asList("東京","中山","阪神","京都",targetRace.getStadium());
        }

        RecentRaceQuery recentRaceQuery = RecentRaceQuery.builder()
                .raceId(id)
                .minRaceLength(targetRace.getRaceLength() <= 1800 ? targetRace.getRaceLength() : 2000)
                .maxRaceLength(targetRace.getRaceLength() <= 1800 ? targetRace.getRaceLength() + 400 : 9999)
                .stadiums(targetStadium)
                .build();

        List<RecentHorseResultDto> recentRaces = raceService.fetchHorseRanRecentRace(recentRaceQuery);

        AtomicInteger sumScore = new AtomicInteger();
        List<HorseScore> horseScores = recentRaces.stream().map(
                recentHorseResultDto -> {
                    AtomicInteger score = new AtomicInteger();
                    AtomicInteger count = new AtomicInteger();
                    recentHorseResultDto.getRaces().forEach(
                            recentRace -> {
                                RaceHorse targetHorse = recentRace.getRaceHorses().stream()
                                        .filter(raceHouse -> Objects.equals(raceHouse.getHorse().getId(), recentHorseResultDto.getRaceHorse().getHorse().getId()))
                                        .findFirst()
                                        .get();

                                double gradeWeight;

                                int gradeDiff = (targetRace.getGrade() == Grade.NONE ? 4 : targetRace.getGrade().getValue()) - (recentRace.getGrade() == Grade.NONE ? 4 : recentRace.getGrade().getValue());
                                if (gradeDiff == 3) {
                                    gradeWeight = 2;
                                } else if (gradeDiff == 2) {
                                    gradeWeight = 1.7;
                                } else if (gradeDiff == 1) {
                                    gradeWeight = 1.3;
                                } else if (gradeDiff == 0) {
                                    gradeWeight = 1;
                                } else if (gradeDiff == -1) {
                                    gradeWeight = 0.7;
                                } else if (gradeDiff == -2) {
                                    gradeWeight = 0.3;
                                } else {
                                    gradeWeight = 0;
                                }

                                double conditionWeight = 1;
                                if(targetRace.getStadium().equals(recentRace.getStadium())) {
                                    conditionWeight = 1.1;
                                }
                                if((targetRace.getRaceLength() <= 1800 && Objects.equals(recentRace.getRaceLength(), targetRace.getRaceLength()))
                                        || (targetRace.getRaceLength() > 1800 && recentRace.getRaceLength() >= targetRace.getRaceLength())) {
                                    conditionWeight = 1.3;
                                }
                                if(((targetRace.getRaceLength() <= 1800 && Objects.equals(recentRace.getRaceLength(), targetRace.getRaceLength()))
                                        || (targetRace.getRaceLength() > 1800 && recentRace.getRaceLength() >= targetRace.getRaceLength()))
                                        && targetRace.getStadium().equals(recentRace.getStadium())
                                ) {
                                    conditionWeight = 1.5;
                                }

                                int rankScore = 0;
                                switch (targetHorse.getRaceResult().getRanking()) {
                                    case 1:
                                        rankScore = 50;
                                        break;
                                    case 2:
                                        rankScore = 40;
                                        break;
                                    case 3:
                                        rankScore = 30;
                                        break;
                                    case 4:
                                        rankScore = 20;
                                        break;
                                    case 5:
                                        rankScore = 10;
                                        break;
                                }

                                int timeScore = 0;
                                if(targetHorse.getRaceResult().calcTargetRaceDevFullTime() >= 70) {
                                    timeScore = 200;
                                } else if (targetHorse.getRaceResult().calcTargetRaceDevFullTime() >= 65) {
                                    timeScore = 125;
                                } else if (targetHorse.getRaceResult().calcTargetRaceDevFullTime() >= 60) {
                                    timeScore = 100;
                                } else if (targetHorse.getRaceResult().calcTargetRaceDevFullTime() >= 55) {
                                    timeScore = 75;
                                } else if (targetHorse.getRaceResult().calcTargetRaceDevFullTime() >= 50) {
                                    timeScore = 50;
                                } else if (targetHorse.getRaceResult().calcTargetRaceDevFullTime() >= 45) {
                                    timeScore = 25;
                                } else if (targetHorse.getRaceResult().calcTargetRaceDevFullTime() >= 40) {
                                    timeScore = 1;
                                }

                                int tmpScore = (int) (gradeWeight * conditionWeight *(timeScore + rankScore));
                                score.addAndGet((int) (gradeWeight * conditionWeight *(timeScore + rankScore)));
                                if(gradeDiff > -3) {
                                    count.addAndGet(1);
                                }
                            }
                    );

                    sumScore.addAndGet(count.get() != 0 ? score.get()/count.get() : 0);
                    return HorseScore.builder()
                            .score(count.get() != 0 ? score.get()/count.get() : 0)
                            .horseId(recentHorseResultDto.getRaceHorse().getHorse().getId())
                            .horseName(recentHorseResultDto.getRaceHorse().getHorse().getName())
                            .build();
                })
                .collect(Collectors.toList());
        return horseScores.stream().map(horseScore -> {
            horseScore.setRate((double) horseScore.getScore()/sumScore.get());
            return horseScore;
        }).collect(Collectors.toList());
   }
}
