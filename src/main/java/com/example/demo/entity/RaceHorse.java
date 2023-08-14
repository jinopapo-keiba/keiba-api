package com.example.demo.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceHorse {
    Integer raceId;
    Integer weight;
    Integer old;
    Integer frameNumber;
    Float handicap;
    Horse horse;
    Jockey jockey;
    Trainer trainer;
    RaceResult raceResult;
}
