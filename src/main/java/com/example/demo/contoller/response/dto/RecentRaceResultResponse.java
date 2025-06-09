package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class RecentRaceResultResponse {
    RaceResponse race;
    RaceHorseResponse raceHorse;
    /**
     * 該当のレースでの全枠の馬のcornerRankingのリスト
     */
    List<CornerRankingResponse> cornerRankings;
}
