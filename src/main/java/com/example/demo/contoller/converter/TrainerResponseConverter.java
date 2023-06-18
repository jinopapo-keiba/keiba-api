package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.TrainerResponse;
import com.example.demo.entity.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerResponseConverter {
    public TrainerResponse convert(Trainer trainer) {
        return TrainerResponse.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .build();
    }
}
