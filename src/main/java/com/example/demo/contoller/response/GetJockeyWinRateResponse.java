package com.example.demo.contoller.response;

import com.example.demo.entity.JockeyWinRate;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetJockeyWinRateResponse {
    List<JockeyWinRate> jockeyWinRates;
    List<Float> jockeyMeanWinRates;
}
