package com.example.demo.contoller.response;

import com.example.demo.contoller.response.dto.RaceResultResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetHorseRaceResultResponse {
    private int id;
    private String name;
    private Integer frameNumber;
    private List<RaceResultResponse> raceResults;
}
