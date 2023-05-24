package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HorseScore {
    int horseId;
    String horseName;
    int score;
    int maxScore;
    double rate;
}
