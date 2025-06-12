package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "レース馬レスポンス")
public class RaceHorseResponse {
    @Schema(description = "馬体重")
    Integer weight;

    @Schema(description = "年齢")
    Integer old;

    @Schema(description = "枠番")
    Integer frameNumber;

    @Schema(description = "ハンデ")
    Integer handicap;

    @Schema(description = "馬情報")
    HorseResponse horse;

    @Schema(description = "騎手情報")
    JockeyResponse jockey;

    @Schema(description = "調教師情報")
    TrainerResponse trainer;

    @Schema(description = "レース結果")
    ResultResponse raceResult;
}
