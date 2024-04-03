package com.example.demo.service;

import com.example.demo.entity.JockeyWinRate;
import com.example.demo.repository.JockeyRepository;
import com.example.demo.repository.dto.JockeyMeanCount;
import com.example.demo.repository.dto.JockeyWinRateMeanParStadium;
import com.example.demo.repository.dto.JockeyWinRateParStadium;
import com.example.demo.utils.DateUtils;
import com.example.demo.valueobject.RaceType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class JockeyService {
    private JockeyRepository jockeyRepository;

    public Map<String, JockeyWinRate> getJockeyWinRatePerStadium(int id, RaceType raceType, String raceDate) throws ParseException {
        Date startDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                                DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                        .minusYears(2)
        );
        Date endDate = DateUtils.getDateFormat().parse(raceDate);

        List<JockeyWinRateParStadium> stadiumScores =  jockeyRepository.fetchJockeyWinRateParStadium(id,raceType,startDate,endDate);
        List<JockeyWinRateMeanParStadium> stadiumMeanScores = jockeyRepository.fetchJockeyMeanWinRateParStadium(raceType,startDate,endDate);
        Map<String,JockeyWinRate> stadiumMap = new HashMap<>();

        stadiumMeanScores.forEach(stadiumMeanScore -> {
            String stadium = stadiumMeanScore.getStadium();
            JockeyWinRateParStadium targetStadiumScore =  stadiumScores.stream()
                    .filter(stadiumScore -> stadiumScore.getStadium().equals(stadium))
                    .findFirst()
                    .orElse(null);
            if (targetStadiumScore == null){
                stadiumMap.put(stadium,
                        JockeyWinRate.builder()
                                .winRate(0)
                                .count(0)
                                .build());
            } else {
                float devi = (targetStadiumScore.getJockeyWinRate().getWinRate() - stadiumMeanScore.getMeanWinRate()) / stadiumMeanScore.getStddevdWinRate() * 10 + 50 ;
                stadiumMap.put(stadium,
                        JockeyWinRate.builder()
                                .winRate(devi)
                                .count(targetStadiumScore.getJockeyWinRate().getCount())
                                .build());
            }
        });

        return stadiumMap;
    }

    public Map<String, Float> getJockeyMeanCount(RaceType raceType, String raceDate) throws ParseException {
        Date startDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                                DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                        .minusYears(2)
        );
        Date endDate = DateUtils.getDateFormat().parse(raceDate);
        List<JockeyMeanCount> jockeyMeanCounts = jockeyRepository.fetchJockeyMeanCount(raceType,startDate,endDate);
        Map<String,Float> map = new HashMap<>();
        jockeyMeanCounts.forEach(
                jockeyMeanCount -> map.put(jockeyMeanCount.getStadium(), jockeyMeanCount.getCount())
        );
        return map;
    }
}
