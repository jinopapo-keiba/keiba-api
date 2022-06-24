package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.RaceResultResponse;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RaceResultResponseConverter {
    private final ResultResponseConverter resultResponseConverter;
    private final RaceResponseConverter raceResponseConverter;

    public RaceResultResponse convert(Race race, RaceResult raceResult){
        return RaceResultResponse.builder()
                .result(resultResponseConverter.convert(raceResult))
                .race(raceResponseConverter.convert(race))
                .build();
    }
}
