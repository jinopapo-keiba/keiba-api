package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "結果レスポンス")
public class ResultResponse {
    @Schema(description = "走破タイムのms", example = "70900")
    long fullTime;

    @Schema(description = "順位", minimum = "1", maximum = "18")
    int ranking;

    @Schema(description = "コーナー順位", example = "1-1-12-14")
    String cornerRanking;

    @Schema(description = "人気", minimum = "1", maximum = "18", example = "1")
    int popular;

    @Schema(description = "最終オッズ", example = "1.8")
    float odds;

    @Schema(description = "上がりタイムのms", example = "36000")
    long lastRapTime;

    @Schema(description = "過去2年に1着になった馬のタイムを母集団としたときの偏差値",example = "51.1")
    float devFullTime;

    @Schema(description = "過去2年に1着になった馬の上がりを母集団としたときの偏差値",example = "51.1")
    float devLastRapTime;

    @Schema(description = "このレースの上位5頭を母集団したときのタイムの偏差値",example = "51.1")
    float devTargetRaceFullTime;

    @Schema(description = "このレースの上位5頭を母集団したときの上がりの偏差値",example = "51.1")
    float devTargetRaceLastRapTime;

    @Schema(description = "devFullTimeを0~1で正規化したもの。正規分布を仮定しており、有効期間は3ds", example = "0.5", minimum = "0", maximum = "1")
    float normalFullTime;

    @Schema(description = "devLastRapTimeを0~1で正規化したもの。正規分布を仮定しており、有効期間は3ds", example = "0.5", minimum = "0", maximum = "1")
    float normalLastRapTime;

    @Schema(description = "devTargetRaceFullTimeを0~1で正規化したもの。正規分布を仮定しており、有効期間は3ds", example = "0.5", minimum = "0", maximum = "1")
    float normalTargetRaceFullTime;

    @Schema(description = "devTargetRaceLastRapTimeを0~1で正規化したもの。正規分布を仮定しており、有効期間は3ds", example = "0.5", minimum = "0", maximum = "1")
    float normalTargetRaceLastRapTime;
}
