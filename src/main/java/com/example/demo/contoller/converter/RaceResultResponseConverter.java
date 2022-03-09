package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.RaceResultResponse;
import com.example.demo.entity.RaceResult;
import org.springframework.stereotype.Component;

@Component
public class RaceResultResponseConverter {
    public RaceResultResponse convert(RaceResult raceResult){
        return RaceResultResponse.builder()
                .fullTime(raceResult.getFullTime().toMillis())
                .ranking(raceResult.getRanking())
                .cornerRanking(raceResult.getCornerRanking())
                .lastRapTime(raceResult.getLastRapTime().toMillis())
                .build();
    }
}
