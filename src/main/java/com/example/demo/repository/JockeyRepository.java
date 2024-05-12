package com.example.demo.repository;

import com.example.demo.entity.Jockey;
import com.example.demo.entity.JockeyWinRate;
import com.example.demo.repository.dto.JockeyMeanCount;
import com.example.demo.repository.dto.JockeyWinRateMeanParStadium;
import com.example.demo.repository.dto.JockeyWinRateParStadium;
import com.example.demo.valueobject.RaceType;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface JockeyRepository {
    Jockey fetchJockey(int id);
    List<Jockey> fetchJockeys(List<String> names);
    void saveJockey(@Param("jockey") Jockey jockey);

    Float fetchJockeyRanking(int id, int raceLength, String stadium, RaceType raceType, Date raceDate);

    @Cacheable("jockeyWinRate")
    JockeyWinRate fetchJockeyWinRate(int id, String stadium, RaceType raceType, Date startDate, Date endDate, int grade);

    @Cacheable("jockeyMeanWinRate")
    Float fetchJockeyMeanWinRate(String stadium, RaceType raceType, Date startDate, Date endDate, int grade);

    @Cacheable("jockeyAllWinRate")
    JockeyWinRate fetchJockeyAllWinRate(int id, Date startDate, Date endDate);

    @Cacheable("jockeyWinRateParStadium")
    List<JockeyWinRateParStadium> fetchJockeyWinRateParStadium(int id, RaceType raceType, Date startDate, Date endDate, int minLength, int maxLength);

    @Cacheable("jockeyAllMeanWinRate")
    Float fetchJockeyAllMeanWinRate(Date startDate, Date endDate);

    @Cacheable("jockeyWinRateParStadium")
    List<JockeyWinRateMeanParStadium> fetchJockeyMeanWinRateParStadium(RaceType raceType, Date startDate, Date endDate, int minLength, int maxLength);

    @Cacheable("jockeyMeanCount")
    @Select(
            "select stadium,AVG(total) as count from (SELECT r.stadium as stadium, r.raceType as raceType, COUNT(*) AS total FROM raceHorse rh JOIN race r ON rh.raceId = r.id JOIN jockey j ON rh.jockeyId = j.id WHERE r.raceDate >= #{startDate} AND r.raceDate < #{endDate} and raceType = #{raceType} and r.raceLength >= #{minLength} AND r.raceLength < #{maxLength} GROUP BY r.stadium, r.raceType,j.id) hoge GROUP BY stadium,raceType order by stadium;"
    )
    List<JockeyMeanCount> fetchJockeyMeanCount(RaceType raceType, Date startDate, Date endDate, int minLength, int maxLength);

}
