package com.example.demo.converter;

import com.example.demo.entity.HorseResult;
import com.example.demo.service.dto.HorseResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class HorseResultConverter {
    public HorseResult converter(HorseResultDto horseResultDto,float devFullTime, float devLastRapTime){
        return HorseResult.builder()
                .race(horseResultDto.getRace())
                .raceResult(horseResultDto.getRaceResult())
                .devFullTime(devFullTime)
                .devLastRapTime(devLastRapTime)
                .build();
    }
}
