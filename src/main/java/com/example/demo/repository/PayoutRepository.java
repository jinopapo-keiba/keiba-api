package com.example.demo.repository;

import com.example.demo.entity.Payout;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PayoutRepository {
    @Insert("INSERT INTO payout (frameNumber,payout,betType,raceId) VALUES (#{payout.frameNumber},#{payout.payout},#{payout.betType},#{raceId})" +
            "ON DUPLICATE KEY UPDATE frameNumber = #{payout.frameNumber},payout = #{payout.payout},betType = #{payout.betType}")
    void savePayout(@Param("payout") Payout payout, @Param("raceId") Integer raceId);
}
