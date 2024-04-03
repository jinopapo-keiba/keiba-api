package com.example.demo.contoller.response;

import com.example.demo.entity.JockeyWinRate;
import com.example.demo.entity.TrainerWinRate;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class GetJockeyWinRateResponse {
    List<JockeyWinRate> jockeyWinRates;
    List<Float> jockeyMeanWinRates;
    JockeyWinRate jockeyAllWinRates;
    Map<String,JockeyWinRate> jockeyAllWinRatesParStadium;
    Float jockeyAllMeanWinRates;
    Map<String,Float> jockeyAllWinRatesMeanParStadium;
}
