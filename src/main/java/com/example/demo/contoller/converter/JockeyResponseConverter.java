package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.JockeyResponse;
import com.example.demo.entity.Jockey;
import org.springframework.stereotype.Component;

@Component
public class JockeyResponseConverter {
    public JockeyResponse convert(Jockey jockey){
        return JockeyResponse.builder()
                .name(jockey.getName())
                .build();
    }
}
