package com.example.demo.repository;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RaceHorseRepository {
    void saveRaceHorse(@Param("raceHorse")RaceHorse raceHorse, @Param("race") Race race);
}
