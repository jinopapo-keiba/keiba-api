package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "コーナー順位レスポンス")
public class CornerRankingResponse {
    Integer frame;
    String ranking;
}
