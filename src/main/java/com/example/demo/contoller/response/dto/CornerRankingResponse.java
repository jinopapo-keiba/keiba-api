package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "コーナー順位レスポンス")
public class CornerRankingResponse {
    @Schema(description = "枠番", minimum = "1", maximum = "18", example = "1")
    Integer frame;

    @Schema(description = "順位",example = "1-1-12-14")
    String ranking;
}
