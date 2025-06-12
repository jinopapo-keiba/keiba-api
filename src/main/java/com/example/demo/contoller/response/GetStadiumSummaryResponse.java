package com.example.demo.contoller.response;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "スタジアム集計レスポンス")
public class GetStadiumSummaryResponse {
    private String name;
    private Long time;
    private Integer count;
}
