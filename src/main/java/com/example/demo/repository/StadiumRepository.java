package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StadiumRepository {
    List<String> fetchRanStadium(@Param("raceId") String raceId,@Param("raceLength") Integer raceLength);
}
