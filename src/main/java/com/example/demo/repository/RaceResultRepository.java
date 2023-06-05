package com.example.demo.repository;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import com.example.demo.entity.RaceResult;
import com.example.demo.entity.StadiumTime;
import com.example.demo.valueobject.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RaceResultRepository {
    void saveRaceResult(@Param("raceResult") RaceResult raceResult, @Param("race") Race race,
                        @Param("raceHorse") RaceHorse raceHorse);
    List<StadiumTime> fetchStadiumSummaryTimes(@Param("time") String time, @Param("raceLength") Integer raceLength,
                                               @Param("grade")Grade grade);
}
