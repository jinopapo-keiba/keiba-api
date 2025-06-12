package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "結果レスポンス")
public class ResultResponse {
    @Schema(description = "走破タイム")
    long fullTime;

    @Schema(description = "順位")
    int ranking;

    @Schema(description = "コーナー順位")
    String cornerRanking;

    @Schema(description = "人気")
    int popular;

    @Schema(description = "オッズ")
    float odds;

    @Schema(description = "上がりタイム")
    long lastRapTime;

    @Schema(description = "タイム差")
    float devFullTime;

    @Schema(description = "上がり差")
    float devLastRapTime;

    @Schema(description = "対象レースタイム差")
    float devTargetRaceFullTime;

    @Schema(description = "対象レース上がり差")
    float devTargetRaceLastRapTime;

    @Schema(description = "平均タイム")
    float normalFullTime;

    @Schema(description = "平均上がり")
    float normalLastRapTime;

    @Schema(description = "対象レース平均タイム")
    float normalTargetRaceFullTime;

    @Schema(description = "対象レース平均上がり")
    float normalTargetRaceLastRapTime;
}
