package com.example.demo.contoller.response;

import com.example.demo.contoller.response.dto.RecentRaceResultResponse;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Getter
@Builder
@Schema(description = "馬のレース結果レスポンス")
public class GetHorseRaceResultResponse {
    @Schema(description = "馬ID")
    private int id;

    @Schema(description = "馬名")
    private String name;

    @Schema(description = "枠番")
    private Integer frameNumber;

    @Schema(description = "ハンデ")
    private Integer handicap;

    @Schema(description = "直近レース結果一覧")
    private List<RecentRaceResultResponse> raceResults;
}
