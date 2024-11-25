package com.example.demo.repository;

import com.example.demo.contoller.request.SaveStadiumRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StadiumRepository {
    @Update(
            "update race set stadiumDay=#{saveStadiumRequest.stadiumDay},stadiumRound=#{saveStadiumRequest.stadiumRound}" +
                    " where raceDate=#{saveStadiumRequest.raceDate} and stadium = #{saveStadiumRequest.stadium} and round = #{saveStadiumRequest.round}"
    )
    void saveStadium(SaveStadiumRequest saveStadiumRequest);
}
