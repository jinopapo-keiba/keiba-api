package com.example.demo.contoller.response;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "スタジアム集計レスポンス")
public class GetStadiumSummaryResponse {
    @Schema(description = "スタジアム名")
    private String name;

    @Schema(description = "合計時間")
    private Long time;

    @Schema(description = "回数")
    private Integer count;
}
