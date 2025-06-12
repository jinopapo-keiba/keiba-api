package com.example.demo.contoller.response;

import com.example.demo.contoller.response.dto.PayoutResponse;
import com.example.demo.contoller.response.dto.RaceHorseResponse;
import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Getter
@Builder
@Schema(description = "レース情報レスポンス")
public class GetRaceResponse {
    String raceName;
    String raceType;
    int raceLength;
    String clockwise;
    String raceCondition;
    String raceWeather;
    String grade;
    String oldLimit;
    String raceDate;
    int id;
    int round;
    String stadium;
    int horseCount;
    Integer stadiumDay;
    Integer stadiumRound;
    List<RaceHorseResponse> raceHorses;
    List<PayoutResponse> payouts;
}
