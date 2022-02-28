package com.example.demo.converter;

import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.entity.Horse;
import com.example.demo.valueobject.HorseGender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class HorseConverter {
    public Horse convert(SaveRaceRequest.Horse horse) {
        return Horse.builder()
                .name(horse.getName())
                .gender(HorseGender.toEnum(horse.getGender()))
                .build();
    }
}
