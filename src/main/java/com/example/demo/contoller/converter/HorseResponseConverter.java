package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.HorseResponse;
import com.example.demo.entity.Horse;
import org.springframework.stereotype.Component;

@Component
public class HorseResponseConverter {
    public HorseResponse convert(Horse horse){
        return HorseResponse.builder()
                .id(horse.getId())
                .name(horse.getName())
                .gender(horse.getGender().getText())
                .build();
    }
}
