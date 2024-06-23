package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JockekMeanCountParStadium {
    String raceType;
    String stadium;
    int count;
}
