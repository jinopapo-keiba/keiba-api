package com.example.demo.contoller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetStadiumSummaryResponse {
    private String name;
    private Long time;
    private Integer count;
}
