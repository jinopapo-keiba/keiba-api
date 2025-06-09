package com.example.demo.contoller.response.v2;

import com.example.demo.entity.JockeyWinRate;
import com.example.demo.valueobject.Range;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetJockeyWinRateResponse {
    String stadium;
    Range range;
    JockeyWinRate jockeyWinRates;
}
