package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.ResultResponse;
import com.example.demo.entity.RaceResult;
import org.springframework.stereotype.Component;

@Component
public class ResultResponseConverter {
    public ResultResponse convert(RaceResult raceResult){
        return ResultResponse.builder()
                .fullTime(raceResult.getFullTime().toMillis())
                .ranking(raceResult.getRanking())
                .cornerRanking(raceResult.getCornerRanking())
                .lastRapTime(raceResult.getLastRapTime().toMillis())
                .devLastRapTime(raceResult.calcDevLastRapTime())
                .devFullTime(raceResult.calcDevFullTime())
                .build();
    }
}
