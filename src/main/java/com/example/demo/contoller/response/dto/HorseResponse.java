package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "馬レスポンス")
public class HorseResponse {
    @Schema(description = "馬ID")
    int id;

    @Schema(description = "馬名")
    String name;

    @Schema(description = "性別")
    String gender;
}
