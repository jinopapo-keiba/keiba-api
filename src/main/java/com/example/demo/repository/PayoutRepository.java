package com.example.demo.repository;

import com.example.demo.entity.Payout;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PayoutRepository {
    @Insert("INSERT INTO payout (frameNumber,payout,betType,raceId) VALUES (#{payout.frameNumber},#{payout.payout},#{payout.betType},#{raceId})")
    void savePayout(@Param("payout") Payout payout, @Param("raceId") Integer raceId);

    @Select("SELECT * FROM payout WHERE raceId = #{raceId}")
    List<Payout> fetchPayout(@Param("raceId") Integer raceId);
}
