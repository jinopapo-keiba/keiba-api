package com.example.demo.repository;

import com.example.demo.entity.Race;
import com.example.demo.repository.dto.RaceQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RaceRepository {
    void saveRace(@Param("race") Race race);
    void updateRace(@Param("race")Race race);
    List<Race> fetchRace(@Param("queryParam")RaceQueryParam queryParam);

    @Select("select distinct(id) from race join raceResult on race.id = raceResult.raceId where raceLength >= 1200 and raceType < 2 and raceDate between \"2020-01-01\" and \"2023-04-01\";")
    List<Integer> fetchAllRace();
    @Select("select id from race join raceResult on race.id = raceResult.raceId where raceLength >= 1200 and raceType < 2 and raceDate > \"2023-04-01\" group by id order by raceDate limit 10;")
    List<Integer> fetchTestGradeRace();

    @Select("select id from race join raceResult on race.id = raceResult.raceId where raceLength >= 1200 and raceType < 2 and raceDate > \"2023-04-01\" group by id order by raceDate limit 90;")
    List<Integer> fetchTestNoneGradeRace();
}
