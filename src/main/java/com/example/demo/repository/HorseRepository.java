package com.example.demo.repository;

import com.example.demo.entity.Horse;
import com.example.demo.repository.dto.SaveHorseQueryParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HorseRepository {
    Horse fetchHorse(int id);
    void saveHorse(SaveHorseQueryParam param);
}
