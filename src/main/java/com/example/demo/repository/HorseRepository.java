package com.example.demo.repository;

import com.example.demo.entity.Horse;
import com.example.demo.repository.dto.HorseQueryParam;
import com.example.demo.repository.dto.SaveHorseQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HorseRepository {
    Horse fetchHorse(int id);
    List<Horse> fetchHorses(HorseQueryParam horseQueryParam);
    void saveHorse(SaveHorseQueryParam param);
    void saveHorses(List<Horse> horses);
}
