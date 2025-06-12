package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "直近レース結果レスポンス")
public class RecentRaceResultResponse {
    RaceResponse race;
    RaceHorseResponse raceHorse;
    /**
     * 該当のレースでの全枠の馬のcornerRankingのリスト
     */
    List<CornerRankingResponse> cornerRankings;
}
