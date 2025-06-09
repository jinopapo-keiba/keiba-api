package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.CornerRankingResponse;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CornerRankingResponseConverter {

    public List<CornerRankingResponse> convert(String cornerRanking, Integer frameNumber) {
        List<CornerRankingResponse> cornerRankings = new ArrayList<>();
        
        if (cornerRanking == null || cornerRanking.isEmpty()) {
            return cornerRankings;
        }
        
        // コーナー順位は通常「1-2-3-4」のような形式で格納されていると仮定
        String[] rankings = cornerRanking.split("-");
        
        for (int i = 0; i < rankings.length; i++) {
            cornerRankings.add(CornerRankingResponse.builder()
                    .frame(i + 1) // コーナー番号（1から開始）
                    .ranking(rankings[i].trim())
                    .build());
        }
        
        return cornerRankings;
    }

    /**
     * レース全体の全馬のコーナー順位を取得する
     */
    public List<CornerRankingResponse> convertAllHorses(Race race) {
        List<CornerRankingResponse> allCornerRankings = new ArrayList<>();
        
        if (race == null || race.getRaceHorses() == null) {
            return allCornerRankings;
        }
        
        for (RaceHorse raceHorse : race.getRaceHorses()) {
            if (raceHorse.getRaceResult() != null && 
                raceHorse.getRaceResult().getCornerRanking() != null) {
                
                allCornerRankings.add(CornerRankingResponse.builder()
                        .frame(raceHorse.getFrameNumber())
                        .ranking(raceHorse.getRaceResult().getCornerRanking())
                        .build());
            }
        }
        
        return allCornerRankings;
    }
}
