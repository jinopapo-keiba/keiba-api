package com.example.demo.contoller.response;

import com.example.demo.contoller.response.dto.RecentRaceResultResponse;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Getter
@Builder
@Schema(description = "馬の直近のレース結果レスポンス")
public class GetHorseRaceResultResponse {
    @Schema(description = "馬ID", example = "123")
    private int id;

    @Schema(description = "馬名", example = "ディープインパクト")
    private String name;

    @Schema(description = "枠番", example = "5", minimum = "1", maximum = "18")
    private Integer frameNumber;

    @Schema(description = "ハンデ", example = "55")
    private Integer handicap;

    @Schema(description = "直近レース結果一覧")
    private List<RecentRaceResultResponse> raceResults;
}
