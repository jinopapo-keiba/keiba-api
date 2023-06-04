package com.example.demo.service;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import com.example.demo.entity.HorseScore;
import com.example.demo.repository.JockeyRepository;
import com.example.demo.service.dto.RecentHorseResultDto;
import com.example.demo.service.dto.RecentRaceQuery;
import com.example.demo.valueobject.Grade;
import com.example.demo.valueobject.HorseGender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreService {
    private final RaceService raceService;
    private final JockeyRepository jockeyRepository;

    public List<HorseScore> calcScore(int id) {
        Race targetRace = raceService.fetchRace(id,true).get(0);

        RecentRaceQuery recentRaceQuery = RecentRaceQuery.builder()
                .raceId(id)
                .build();

        List<RecentHorseResultDto> recentRaces = raceService.fetchHorseRanRecentRace(recentRaceQuery);

        AtomicInteger sumScore = new AtomicInteger();
        List<HorseScore> horseScores = recentRaces.stream().map(
                recentHorseResultDto -> {
                    AtomicInteger score = new AtomicInteger();
                    AtomicInteger maxScore = new AtomicInteger();
                    AtomicInteger count = new AtomicInteger();
                    recentHorseResultDto.getRaces().forEach(
                            recentRace -> {
                                RaceHorse targetHorse = recentRace.getRaceHorses().stream()
                                        .filter(raceHouse -> Objects.equals(raceHouse.getHorse().getId(), recentHorseResultDto.getRaceHorse().getHorse().getId()))
                                        .findFirst()
                                        .get();

                                double gradeScore = 1 - (double) (recentRace.getGrade() == Grade.NONE ? 4 : recentRace.getGrade().getValue()) /4;
                                double stadiumScore = Objects.equals(targetRace.getStadium(), recentRace.getStadium()) ? 1 : 0;
                                double rankScore = 1- (double) targetHorse.getRaceResult().getRanking()/18;
                                double fullTimeScore = targetHorse.getRaceResult().calcNormalizeFulltime();
                                double lastRapTimeScore = targetHorse.getRaceResult().calcNormalizeLastRapTime();
                                double raceFullTimeScore = targetHorse.getRaceResult().calcNormalizeRaceFulltime();
                                double raceLastRapTimeScore = targetHorse.getRaceResult().calcNormalizeRaceLastRapTime();
                                double sexScore = targetHorse.getHorse().getGender() == HorseGender.MAN ? 1 : 0;
                                double raceTypeScore = targetRace.getRaceType() == recentRace.getRaceType() ? 1 : 0;
                                double lengthScore = Objects.equals(targetRace.getRaceLength(), recentRace.getRaceLength()) ? 1 : 0;
                                double lengthDiffScore = (double) Math.abs(targetRace.getRaceLength() - recentRace.getRaceLength()) /2600;
                                double frameScore = (double) targetHorse.getFrameNumber()/18;
                                double oldScore = (double) Math.abs(targetHorse.getOld() - 4) /5;
                                Float jockeyRanking = jockeyRepository.fetchJockeyRanking(targetHorse.getJockey().getId(),recentRace.getRaceLength(),recentRace.getStadium(),recentRace.getRaceType());
                                if(jockeyRanking == null) {
                                    jockeyRanking = (float) 0;
                                }
                                double jockeyScore = 1-jockeyRanking/18;
                                int tmpScore = (int) ((gradeScore*1.5+rankScore+fullTimeScore*0.5+lastRapTimeScore*0.1+raceFullTimeScore*0.8+raceLastRapTimeScore*0.2+sexScore*0.2+frameScore*0.1+oldScore*0.2+raceTypeScore+lengthDiffScore+lengthScore*0.3+lengthScore*stadiumScore*0.2+jockeyScore*0.2)*100);
                                score.addAndGet(tmpScore);
                                maxScore.set(Math.max(maxScore.get(),tmpScore));
                                count.addAndGet(1);
                            }
                    );

                    double frameScore = 1 - (double) recentHorseResultDto.getRaceHorse().getFrameNumber() /18;
                    double oldScore = 1 - (double) Math.abs(recentHorseResultDto.getRaceHorse().getOld() - 4) /5;
                    Float jockeyRanking = jockeyRepository.fetchJockeyRanking(recentHorseResultDto.getRaceHorse().getJockey().getId(),targetRace.getRaceLength(),targetRace.getStadium(),targetRace.getRaceType());
                    if(jockeyRanking == null) {
                        jockeyRanking = (float) 0;
                    }
                    double jockeyScore = 1 - jockeyRanking/18;

                    int tmpScore = count.get() != 0 ? score.get()/count.get() : 0;
                    tmpScore += (int)((frameScore*0.1+oldScore*0.1+jockeyScore*0.2)*100);
                    maxScore.getAndAdd((int)((frameScore*(-0.1)+oldScore*(-0.1))*100));
                    if(tmpScore < 0) {
                        tmpScore = 0;
                    }
                    sumScore.addAndGet(tmpScore);
                    return HorseScore.builder()
                            .score(tmpScore)
                            .maxScore(maxScore.get())
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
