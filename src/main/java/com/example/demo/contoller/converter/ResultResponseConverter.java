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
                .popelar(raceResult.getPopular())
                .lastRapTime(raceResult.getLastRapTime().toMillis())
                .devLastRapTime(raceResult.calcDevLastRapTime())
                .devFullTime(raceResult.calcDevFullTime())
                .devTargetRaceFullTime(raceResult.calcTargetRaceDevFullTime())
                .devTargetRaceLastRapTime(raceResult.calcTargetRaceDevLastRapTime())
                .normalLastRapTime((float) raceResult.calcNormalizeLastRapTime())
                .normalFullTime((float) raceResult.calcNormalizeFulltime())
                .normalTargetRaceLastRapTime((float) raceResult.calcNormalizeRaceLastRapTime())
                .normalTargetRaceFullTime((float) raceResult.calcNormalizeRaceFulltime())
                .build();
    }
}
