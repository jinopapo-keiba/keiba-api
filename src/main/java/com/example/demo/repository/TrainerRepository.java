package com.example.demo.repository;

import com.example.demo.entity.Trainer;
import com.example.demo.entity.TrainerWinRate;
import com.example.demo.repository.dto.TrainerMeanCount;
import com.example.demo.repository.dto.TrainerWinRateMeanParStadium;
import com.example.demo.repository.dto.TrainerWinRateParStadium;
import com.example.demo.valueobject.RaceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.Date;
import java.util.List;

@Mapper
public interface TrainerRepository {
    List<Trainer> fetchTrainers(List<String> names);
    void saveTrainer(@Param("trainer") Trainer trainer);

    Float fetchTrainerRanking(int id, int raceLength, String stadium, RaceType raceType, Date raceDate);

    @Cacheable("trainerWinRate")
    TrainerWinRate fetchTrainerWinRate(int id, Date startDate, Date endDate);

    @Cacheable("trainerWinRateParStadium")
    List<TrainerWinRateParStadium> fetchTrainerWinRateParStadium(int id, RaceType raceType, Date startDate, Date endDate);

    @Cacheable("trainerMeanWinRate")
    Float fetchTrainerMeanWinRate(Date startDate, Date endDate);

    @Cacheable("trainerWinRateParStadium")
    List<TrainerWinRateMeanParStadium> fetchTrainerMeanWinRateParStadium(RaceType raceType, Date startDate, Date endDate);

    @Cacheable("trainerMeanCount")
    @Select(
            "select stadium,AVG(total) as count from (SELECT r.stadium as stadium, r.raceType as raceType, COUNT(*) AS total FROM raceHorse rh JOIN race r ON rh.raceId = r.id JOIN trainer j ON rh.trainerId = j.id WHERE r.raceDate >= #{startDate} AND r.raceDate < #{endDate} and raceType = #{raceType} GROUP BY r.stadium, r.raceType,j.id) hoge GROUP BY stadium,raceType order by stadium;"
    )
    List<TrainerMeanCount> fetchTrainerMeanCount(RaceType raceType, Date startDate, Date endDate);

}