package com.example.demo.repository;

import com.example.demo.entity.Jockey;
import com.example.demo.valueobject.RaceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JockeyRepository {
    Jockey fetchJockey(int id);
    List<Jockey> fetchJockeys(List<String> names);
    void saveJockey(@Param("jockey") Jockey jockey);

    Float fetchJockeyRanking(int id, int raceLength, String stadium, RaceType raceType);
}
