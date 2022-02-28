package com.example.demo.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RaceHorse {
    Horse horse;
    Jockey jockey;
    Integer weight;
    Integer old;
    Integer frameNumber;
    RaceResult raceResult;
}
