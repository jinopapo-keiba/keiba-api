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
    List<JockeyWinRate> jockeyWinRates;
    List<Float> jockeyMeanWinRates;
    JockeyWinRate jockeyAllWinRates;
    Map<String,JockeyWinRate> jockeyAllWinRatesParStadium;
    Float jockeyAllMeanWinRates;
    Map<String,Float> jockeyAllWinRatesMeanParStadium;
}
