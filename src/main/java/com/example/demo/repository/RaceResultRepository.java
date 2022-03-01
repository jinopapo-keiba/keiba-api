package com.example.demo.repository;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import com.example.demo.entity.RaceResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RaceResultRepository {
    void saveRaceResult(@Param("raceResult") RaceResult raceResult, @Param("race") Race race, @Param("raceHorse") RaceHorse raceHorse);
}
