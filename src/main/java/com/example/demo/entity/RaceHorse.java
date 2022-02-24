package com.example.demo.entity;

import lombok.Value;

@Value
public class RaceHorse {
    Horse horse;
    Jockey jockey;
    Integer weight;
    Integer old;
    Integer frameNumber;
    RaceResult raceResult;
}
