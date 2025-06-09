package com.example.demo.repository;

import com.example.demo.contoller.request.SaveStadiumRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StadiumRepository {
    @Update(
            "update race set stadiumDay=#{stadiumDay},stadiumRound=#{stadiumRound}" +
                    " where raceDate=#{raceDate} and stadium = #{stadium} and round = #{round}"
    )
    void saveStadium(SaveStadiumRequest saveStadiumRequest);
}
