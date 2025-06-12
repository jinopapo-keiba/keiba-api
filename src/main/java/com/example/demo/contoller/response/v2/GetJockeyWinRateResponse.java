package com.example.demo.contoller.response.v2;

import com.example.demo.entity.JockeyWinRate;
import com.example.demo.valueobject.Range;
import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Builder
@Data
@Schema(description = "騎手勝率レスポンス v2")
public class GetJockeyWinRateResponse {
    String stadium;
    Range range;
    JockeyWinRate jockeyWinRates;
}
