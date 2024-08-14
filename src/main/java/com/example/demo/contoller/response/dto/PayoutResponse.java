package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayoutResponse {
    String frameNumber;
    Float payout;
    String betType;
}
