package com.example.demo.repository;

import com.example.demo.entity.Trainer;
import com.example.demo.valueobject.RaceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TrainerRepository {
    List<Trainer> fetchTrainers(List<String> names);
    void saveTrainer(@Param("trainer") Trainer trainer);

    Float fetchTrainerRanking(int id, int raceLength, String stadium, RaceType raceType, Date raceDate);
}