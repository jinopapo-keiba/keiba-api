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
    @Schema(description = "レース名", example = "日本ダービー")
    String raceName;

    @Schema(description = "レース種別", example = "芝",
            allowableValues = {"芝","ダート","障害"})
    String raceType;

    @Schema(description = "距離", example = "2400", minimum = "1000", maximum = "3600")
    int raceLength;

    @Schema(description = "回り", example = "右",
            allowableValues = {"左","右"})
    String clockwise;

    @Schema(description = "馬場状態", example = "良",
            allowableValues = {"良","稍重","重","不良"})
    String raceCondition;

    @Schema(description = "天気", example = "晴",
            allowableValues = {"晴","曇","雨","小雨"})
    String raceWeather;

    @Schema(description = "グレード", example = "G1",
            allowableValues = {"G1","G2","G3","リステッド","オープン","3勝クラス",
                    "2勝クラス","1勝クラス","新馬","未勝利"})
    String grade;

    @Schema(description = "年齢制限", example = "3歳以上",
            allowableValues = {"2歳","3歳","3歳以上","4歳以上"})
    String oldLimit;

    @Schema(description = "開催日", example = "2023-05-28")
    String raceDate;

    @Schema(description = "レースID", example = "202301010801")
    int id;

    @Schema(description = "回数", example = "11")
    int round;

    @Schema(description = "スタジアム", example = "東京")
    String stadium;

    @Schema(description = "出走頭数", example = "18")
    int horseCount;

    @Schema(description = "開催日番号", example = "2")
    Integer stadiumDay;

    @Schema(description = "開催回番号", example = "1")
    Integer stadiumRound;

    @Schema(description = "レース馬情報")
    List<RaceHorseResponse> raceHorses;

    @Schema(description = "払戻情報")
    List<PayoutResponse> payouts;
}
