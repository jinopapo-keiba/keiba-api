package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JockekMeanCountParRange {
    String raceType;
    String range;
    int count;
}
