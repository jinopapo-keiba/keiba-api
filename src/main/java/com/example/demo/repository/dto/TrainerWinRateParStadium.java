package com.example.demo.repository.dto;

import com.example.demo.entity.TrainerWinRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerWinRateParStadium {
    private String stadium;
    private TrainerWinRate trainerWinRate;
}
