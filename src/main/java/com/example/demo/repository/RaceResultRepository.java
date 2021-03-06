package com.example.demo.repository;

import com.example.demo.entity.*;
import com.example.demo.service.dto.BestRaceTimeDto;
import com.example.demo.service.dto.HorseResultDto;
import com.example.demo.valueobject.Grade;
import com.example.demo.valueobject.RaceCondition;
import com.example.demo.valueobject.SummaryType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RaceResultRepository {
    void saveRaceResult(@Param("raceResult") RaceResult raceResult, @Param("race") Race race,
                        @Param("raceHorse") RaceHorse raceHorse);
    List<BestRaceTimeDto> fetchBestRaceTimes(@Param("stadium") String stadium, @Param("raceLength") Integer raceLength,
                                             @Param("raceId") String raceId, @Param("summaryType") SummaryType summaryType,
                                             @Param("raceCondition") RaceCondition raceCondition);
    List<StadiumTime> fetchStadiumSummaryTimes(@Param("time") String time, @Param("raceLength") Integer raceLength,
                                               @Param("grade")Grade grade);
    RaceResultSummary fetchRaceResultSummary(@Param("stadium") String stadium, @Param("raceLength") Integer raceLength,
                                             @Param("raceCondition") RaceCondition raceCondition,
                                             @Param("grade")Grade grade, @Param("raceId") String raceId);
    List<HorseResultDto> fetchHorseResult(@Param("horseId") Integer horseId);
}
