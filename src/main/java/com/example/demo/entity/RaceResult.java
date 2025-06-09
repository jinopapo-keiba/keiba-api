package com.example.demo.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaceResult {
    Duration fullTime;
    Integer ranking;
    String cornerRanking;
    Integer popular;
    Float odds;
    Duration lastRapTime;
    Duration meanFullTime;
    Duration meanLastRapTime;
    Float stdDeviFullTime;
    Float stdDeviLastRapTime;
    Duration meanTargetRaceFullTime;
    Duration meanTargetRaceLastRapTime;
    Float stdDeviTargetRaceFullTime;
    Float stdDeviTargetRaceLastRapTime;
    // 99%のデータを正しく表現するために標準偏差の3倍で正規化する(3sd)
    private final Integer sd = 3;

    public float calcDevFullTime() {
        if(fullTime.toMillis() == 0 || stdDeviFullTime == null) {
            return 0;
        }
        return (((meanFullTime.toMillis() - fullTime.toMillis()) * 10) / stdDeviFullTime) + 50;
    }

    public float calcDevLastRapTime() {
        if(lastRapTime.toMillis() == 0 || getStdDeviLastRapTime() == null) {
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

    public double calcNormalizeFulltime() {
        log.warn("標準偏差欠損");
        if(stdDeviFullTime == null || stdDeviFullTime == 0){
            return 0;
        }

        double tmpScore =  ((meanFullTime.toMillis() - fullTime.toMillis())/ stdDeviFullTime);
        if (tmpScore < -sd) {
            tmpScore = -sd;
        } else if (tmpScore > sd) {
            tmpScore = sd;
        }
        return tmpScore/sd/2 +0.5 ;
    }

    public double calcNormalizeLastRapTime() {
        if(stdDeviLastRapTime == null || stdDeviLastRapTime == 0){
            log.warn("標準偏差欠損");
            return 0;
        }
        double tmpScore =  ((meanLastRapTime.toMillis() - lastRapTime.toMillis())/ stdDeviLastRapTime);
        if (tmpScore < -sd) {
            tmpScore = -sd;
        } else if (tmpScore > sd) {
            tmpScore = sd;
        }
        return tmpScore/sd/2 +0.5;
    }

    public double calcNormalizeRaceFulltime() {
        if(stdDeviTargetRaceFullTime == null || stdDeviTargetRaceFullTime == 0){
            log.warn("レース内標準偏差欠損");
            return 0;
        }
        double tmpScore =  ((meanTargetRaceFullTime.toMillis() - fullTime.toMillis())/ stdDeviTargetRaceFullTime);
        if (tmpScore < -sd) {
            tmpScore = -sd;
        } else if (tmpScore > sd) {
            tmpScore = sd;
        }
        return tmpScore/sd/2 +0.5;
    }

    public double calcNormalizeRaceLastRapTime() {
        if(stdDeviTargetRaceLastRapTime == null || stdDeviTargetRaceLastRapTime == 0){
            log.warn("レース内標準偏差欠損");
            return 0;
        }
        double tmpScore =  ((meanTargetRaceLastRapTime.toMillis() - lastRapTime.toMillis())/ stdDeviTargetRaceLastRapTime);

        if (tmpScore < -sd) {
            tmpScore = -sd;
        } else if (tmpScore > sd) {
            tmpScore = sd;
        }
        return tmpScore/sd/2 +0.5;
    }
}
