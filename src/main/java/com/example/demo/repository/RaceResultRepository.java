package com.example.demo.repository;

import com.example.demo.entity.*;
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
    List<BestRaceTime> fetchBestRaceTimes(@Param("stadium") String stadium, @Param("raceLength") Integer raceLength,
                                          @Param("raceId") String raceId, @Param("summaryType") SummaryType summaryType,
                                          @Param("raceCondition") RaceCondition raceCondition);
    List<StadiumTime> fetchStadiumSummaryTimes(@Param("time") String time, @Param("raceLength") Integer raceLength, @Param("grade")Grade grade);
}
