package com.example.demo.repository;

import com.example.demo.entity.Trainer;
import com.example.demo.entity.TrainerWinRate;
import com.example.demo.repository.dto.TrainerWinRateMeanParStadium;
import com.example.demo.repository.dto.TrainerWinRateParStadium;
import com.example.demo.valueobject.RaceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface TrainerRepository {
    List<Trainer> fetchTrainers(List<String> names);
    void saveTrainer(@Param("trainer") Trainer trainer);

    Float fetchTrainerRanking(int id, int raceLength, String stadium, RaceType raceType, Date raceDate);

    @Cacheable("trainerWinRate")
    TrainerWinRate fetchTrainerWinRate(int id, Date startDate, Date endDate);

    @Cacheable("trainerWinRateParStadium")
    List<TrainerWinRateParStadium> fetchTrainerWinRateParStadium(int id, Date startDate, Date endDate);

    @Cacheable("trainerMeanWinRate")
    Float fetchTrainerMeanWinRate(Date startDate, Date endDate);

    @Cacheable("trainerWinRateParStadium")
    List<TrainerWinRateMeanParStadium> fetchTrainerMeanWinRateParStadium(Date startDate, Date endDate);


}