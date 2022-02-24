package com.example.demo.repository;

import com.example.demo.entity.Race;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RaceRepository {
    void saveRace(@Param("race") Race race);
}
