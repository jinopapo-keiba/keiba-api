package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@Schema(description = "払戻情報レスポンス")
public class PayoutResponse {
    String frameNumber;
    Float payout;
    String betType;
}
