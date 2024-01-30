package com.example.demo.contoller.response;

import com.example.demo.entity.TrainerWinRate;
import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Builder
@Data
public class GetTrainerWinRateResponse {
    TrainerWinRate trainerAllWinRates;
    Map<String,TrainerWinRate> trainerAllWinRatesParStadium;
    Float trainerAllMeanWinRates;
    Map<String,Float> trainerAllWinRatesMeanParStadium;
}
