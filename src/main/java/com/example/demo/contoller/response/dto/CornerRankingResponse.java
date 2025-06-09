package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CornerRankingResponse {
    Integer frame;
    String ranking;
}
