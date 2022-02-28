package com.example.demo.repository;

import com.example.demo.entity.Horse;
import com.example.demo.repository.dto.HorseQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HorseRepository {
    Horse fetchHorse(int id);
    List<Horse> fetchHorses(HorseQueryParam horseQueryParam);
    void saveHorse(@Param("horse") Horse horse);
}
