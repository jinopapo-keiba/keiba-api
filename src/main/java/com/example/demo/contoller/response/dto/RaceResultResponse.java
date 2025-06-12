package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "レース結果レスポンス")
public class RaceResultResponse {
    @Schema(description = "レース情報")
    RaceResponse race;

    @Schema(description = "出走馬情報")
    RaceHorseResponse raceHorse;
}
