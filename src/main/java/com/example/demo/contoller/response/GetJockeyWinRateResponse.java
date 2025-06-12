package com.example.demo.contoller.response;

import com.example.demo.entity.JockeyWinRate;
import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

@Builder
@Data
@Schema(description = "騎手勝率レスポンス")
public class GetJockeyWinRateResponse {
    @Schema(description = "騎手勝率一覧")
    List<JockeyWinRate> jockeyWinRates;

    @Schema(description = "騎手平均勝率一覧")
    List<Float> jockeyMeanWinRates;

    @Schema(description = "全体勝率")
    JockeyWinRate jockeyAllWinRates;

    @Schema(description = "スタジアム別勝率")
    Map<String,JockeyWinRate> jockeyAllWinRatesParStadium;

    @Schema(description = "全体平均勝率")
    Float jockeyAllMeanWinRates;

    @Schema(description = "スタジアム別平均勝率")
    Map<String,Float> jockeyAllWinRatesMeanParStadium;
}
