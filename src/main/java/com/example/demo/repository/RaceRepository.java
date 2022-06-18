package com.example.demo.repository;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceLength;
import com.example.demo.repository.dto.RaceQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RaceRepository {
    void saveRace(@Param("race") Race race);
    void updateRace(@Param("race")Race race);
    List<Race> fetchRace(@Param("queryParam")RaceQueryParam queryParam);
    List<RaceLength> fetchRanRaceLength(@Param("raceId")String raceId);
}
