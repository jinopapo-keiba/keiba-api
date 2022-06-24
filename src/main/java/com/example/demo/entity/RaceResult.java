package com.example.demo.entity;

import lombok.*;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceResult {
    Duration fullTime;
    Integer ranking;
    String cornerRanking;
    Duration lastRapTime;
    Float meanFullTime;
    Float meanLastRapTime;
    Float stdDeviFullTime;
    Float stdDeviLastRapTime;

    public float calcDevFullTime() {
        if(fullTime.toMillis() == 0) {
            return 0;
        }
        return (((meanFullTime - fullTime.toMillis()) * 10) / stdDeviFullTime) + 50;
    }

    public float calcDevLastRapTime() {
        if(lastRapTime.toMillis() == 0) {
            return 0;
        }
        return (((meanLastRapTime - lastRapTime.toMillis()) * 10) / stdDeviLastRapTime) + 50;
    }
}
