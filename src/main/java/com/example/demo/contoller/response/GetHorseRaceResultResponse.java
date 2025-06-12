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
    private int id;
    private String name;
    private Integer frameNumber;
    private Integer handicap;
    private List<RecentRaceResultResponse> raceResults;
}
