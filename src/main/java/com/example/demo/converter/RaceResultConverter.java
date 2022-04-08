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
        Pattern fullTimePattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)");
        Matcher fullTimeMatcher = fullTimePattern.matcher(raceResult.getFullTime());
        // matchesが走らないとgroupが取れない
        if(!fullTimeMatcher.matches()) {
            return RaceResult.builder()
                    .fullTime(Duration.ZERO)
                    .ranking(raceResult.getRanking())
                    .lastRapTime(Duration.ZERO)
                    .cornerRanking(raceResult.getCornerRanking())
                    .build();
        }
        fullTimeMatcher.matches();
        Duration fullTime = Duration.ofMillis(
                Long.parseLong(fullTimeMatcher.group(1)) * 60000
                        + Long.parseLong(fullTimeMatcher.group(2)) * 1000
                        + Long.parseLong(fullTimeMatcher.group(3)) * 100
        );
        Pattern lastRapTimePattern = Pattern.compile("(\\d+)\\.(\\d+)");
        Matcher lastRapTimeMatcher = lastRapTimePattern.matcher(raceResult.getLastRapTime());
        // matchesが走らないとgroupが取れない
        lastRapTimeMatcher.matches();
        Duration lastRapTime = Duration.ofMillis(
                Long.parseLong(lastRapTimeMatcher.group(1))*1000
                        + Long.parseLong(lastRapTimeMatcher.group(2))*100
        );
        return RaceResult.builder()
                .fullTime(fullTime)
                .ranking(raceResult.getRanking())
                .lastRapTime(lastRapTime)
                .cornerRanking(raceResult.getCornerRanking())
                .build();
    }
}
