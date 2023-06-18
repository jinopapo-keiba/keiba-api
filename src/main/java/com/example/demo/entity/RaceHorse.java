package com.example.demo.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceHorse {
    Integer weight;
    Integer old;
    Integer frameNumber;
    Horse horse;
    Jockey jockey;
    Trainer trainer;
    RaceResult raceResult;
    Float handicap;
}
