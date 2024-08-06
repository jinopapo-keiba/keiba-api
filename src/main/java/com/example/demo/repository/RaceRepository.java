package com.example.demo.repository;

import com.example.demo.entity.Race;
import com.example.demo.repository.dto.DeviBase;
import com.example.demo.repository.dto.RaceQueryParam;
import com.example.demo.service.dto.MajorGradeRateDto;
import com.example.demo.valueobject.RaceCondition;
import com.example.demo.valueobject.RaceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.Date;
import java.util.List;

@Mapper
public interface RaceRepository {
    void saveRace(@Param("race") Race race);
    void updateRace(@Param("race")Race race);
    List<Race> fetchRace(@Param("queryParam")RaceQueryParam queryParam);
    @Cacheable("devi")
    DeviBase fetchDeviBase(@Param("raceType") RaceType raceType, @Param("raceCondition") RaceCondition raceCondition, @Param("stadium") String stadium, @Param("raceLength") Integer raceLength);

    @Select("select distinct(id) from race join raceResult on race.id = raceResult.raceId where raceLength >= 1200 and raceType < 2 and grade in (0,1,2,3,4,5,6,7,9) and raceDate between \"1992-01-01\" and \"2023-07-01\";")
    List<Integer> fetchAllRace();
    @Select("select distinct(id) from race join raceResult on race.id = raceResult.raceId where raceLength >= 1200 and raceType < 2 and grade in (0,1,2,3,4,5,6,7,9) and raceDate between \"2023-07-01\" and \"2024-07-01\";")
    List<Integer> fetchTestRace();

    @Cacheable("majorGradeRate")
    @Select("SELECT stadium,SUM(CASE WHEN r.grade <= 2 THEN 1 ELSE 0 END) / COUNT(*) as rate FROM race r where r.raceDate between #{startDate} and #{endDate} GROUP BY r.stadium;")
    List<MajorGradeRateDto> fetchMajorGradeRate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Select("select id from race where raceDate = #{date} and stadium = #{stadium} and round  = #{round}")
    String fetchRaceId(@Param("date") Date date,@Param("stadium") String stadium, @Param("round") Integer round);
}
