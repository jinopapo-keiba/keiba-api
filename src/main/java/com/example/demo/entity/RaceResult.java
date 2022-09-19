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
    Duration meanFullTime;
    Duration meanLastRapTime;
    Float stdDeviFullTime;
    Float stdDeviLastRapTime;
    Duration meanTargetRaceFullTime;
    Duration meanTargetRaceLastRapTime;
    Float stdDeviTargetRaceFullTime;
    Float stdDeviTargetRaceLastRapTime;

    public float calcDevFullTime() {
        if(fullTime.toMillis() == 0) {
            return 0;
        }
        return (((meanFullTime.toMillis() - fullTime.toMillis()) * 10) / stdDeviFullTime) + 50;
    }

    public float calcDevLastRapTime() {
        if(lastRapTime.toMillis() == 0) {
            return 0;
        }
        return (((meanLastRapTime.toMillis() - lastRapTime.toMillis()) * 10) / stdDeviLastRapTime) + 50;
    }

    public float calcTargetRaceDevFullTime() {
        if(fullTime.toMillis() == 0) {
            return 0;
        }
        return (((meanTargetRaceFullTime.toMillis() - fullTime.toMillis()) * 10) / stdDeviTargetRaceFullTime) + 50;
    }

    public float calcTargetRaceDevLastRapTime() {
        if(lastRapTime.toMillis() == 0) {
            return 0;
        }
        return (((meanTargetRaceLastRapTime.toMillis() - lastRapTime.toMillis()) * 10) / stdDeviTargetRaceLastRapTime) + 50;
    }

}
