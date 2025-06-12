package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "レースレスポンス")
public class RaceResponse {
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
    int stadiumDay;

    @Schema(description = "開催回番号")
    int stadiumRound;
}
