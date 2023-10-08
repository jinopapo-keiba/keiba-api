package com.example.demo.repository;

import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import com.example.demo.entity.RaceResult;
import com.example.demo.entity.StadiumTime;
import com.example.demo.valueobject.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RaceResultRepository {
    void saveRaceResult(@Param("raceResult") RaceResult raceResult, @Param("race") Race race,
                        @Param("raceHorse") RaceHorse raceHorse);
    List<StadiumTime> fetchStadiumSummaryTimes(@Param("time") String time, @Param("raceLength") Integer raceLength,
                                               @Param("grade")Grade grade);

    /**
     * 勝ち馬を人気別に集計した結果を取得する
     *
     * @return 集計データ
     */
    @Select("select count(*) as total from race join raceResult on race.id = raceResult.raceId where raceLength >= 1200 and raceType < 2 and grade in (0,1,2,3,4,5,6,7,9) and raceDate between \"2018-01-01\" and \"2023-08-31\" and ranking = 1 and popular > 0 and popular < 10 group by popular order by popular;")
    List<Integer> fetchWinHorsePopular();
}
