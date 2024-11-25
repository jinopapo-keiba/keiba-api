package com.example.demo.converter;

import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.entity.RaceResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
@Slf4j
public class RaceResultConverter {
    public RaceResult converter(SaveRaceRequest.RaceResult raceResult){
        Pattern fullTimePattern = Pattern.compile("((\\d+):)?(\\d+)\\.(\\d+)");
        Matcher fullTimeMatcher = fullTimePattern.matcher(raceResult.getFullTime());
        Pattern lastRapTimePattern = Pattern.compile("((\\d+):)?(\\d+)\\.(\\d+)");
        Matcher lastRapTimeMatcher = lastRapTimePattern.matcher(raceResult.getLastRapTime());
        // matchesが走らないとgroupが取れない
        if(!fullTimeMatcher.matches() || !lastRapTimeMatcher.matches()) {
            return RaceResult.builder()
                    .fullTime(Duration.ZERO)
                    .ranking(raceResult.getRanking())
                    .lastRapTime(Duration.ZERO)
                    .popular(raceResult.getPopular())
                    .odds(raceResult.getOdds())
                    .cornerRanking(raceResult.getCornerRanking())
                    .build();
        }
        Duration fullTime = Duration.ofMillis(
                (fullTimeMatcher.group(2) != null ? Long.parseLong(fullTimeMatcher.group(2)) : 0) * 60000
                        + Long.parseLong(fullTimeMatcher.group(3)) * 1000
                        + Long.parseLong(fullTimeMatcher.group(4)) * 100
        );
        // matchesが走らないとgroupが取れない
        Duration lastRapTime = Duration.ofMillis(
                (lastRapTimeMatcher.group(2) != null ? Long.parseLong(lastRapTimeMatcher.group(2)) : 0) * 60000
                        + Long.parseLong(lastRapTimeMatcher.group(3))*1000
                        + Long.parseLong(lastRapTimeMatcher.group(4))*100
        );
        return RaceResult.builder()
                .fullTime(fullTime)
                .ranking(raceResult.getRanking())
                .lastRapTime(lastRapTime)
                .popular(raceResult.getPopular())
                .odds(raceResult.getOdds())
                .cornerRanking(raceResult.getCornerRanking())
                .build();
    }
}
