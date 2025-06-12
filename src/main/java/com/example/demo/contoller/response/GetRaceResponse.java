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
    @Schema(description = "レース名")
    String raceName;

    @Schema(description = "レース種別")
    String raceType;

    @Schema(description = "距離")
    int raceLength;

    @Schema(description = "回り")
    String clockwise;

    @Schema(description = "馬場状態")
    String raceCondition;

    @Schema(description = "天気")
    String raceWeather;

    @Schema(description = "グレード")
    String grade;

    @Schema(description = "年齢制限")
    String oldLimit;

    @Schema(description = "開催日")
    String raceDate;

    @Schema(description = "レースID")
    int id;

    @Schema(description = "回数")
    int round;

    @Schema(description = "スタジアム")
    String stadium;

    @Schema(description = "出走頭数")
    int horseCount;

    @Schema(description = "開催日番号")
    Integer stadiumDay;

    @Schema(description = "開催回番号")
    Integer stadiumRound;

    @Schema(description = "レース馬情報")
    List<RaceHorseResponse> raceHorses;

    @Schema(description = "払戻情報")
    List<PayoutResponse> payouts;
}
