package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@Schema(description = "払戻情報レスポンス")
public class PayoutResponse {
    @Schema(description = "枠番号")
    String frameNumber;

    @Schema(description = "払戻額")
    Float payout;

    @Schema(description = "馬券種別")
    String betType;
}
