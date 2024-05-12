package com.example.demo.service;

import com.example.demo.contoller.response.v2.GetJockeyMeanCountResponse;
import com.example.demo.contoller.response.v2.GetJockeyWinRateResponse;
import com.example.demo.entity.JockeyWinRate;
import com.example.demo.repository.JockeyRepository;
import com.example.demo.repository.dto.JockeyMeanCount;
import com.example.demo.repository.dto.JockeyWinRateMeanParStadium;
import com.example.demo.repository.dto.JockeyWinRateParStadium;
import com.example.demo.utils.DateUtils;
import com.example.demo.valueobject.RaceType;
import com.example.demo.valueobject.Range;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class JockeyService {
    private JockeyRepository jockeyRepository;

    public List<GetJockeyWinRateResponse> getJockeyWinRatePerStadium(int id, RaceType raceType, String raceDate) throws ParseException {
        Date startDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                                DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                        .minusYears(2)
        );
        Date endDate = DateUtils.getDateFormat().parse(raceDate);
        List<GetJockeyWinRateResponse> responses = new ArrayList<>();

        for (Range range : Range.values()) {
            List<JockeyWinRateParStadium> stadiumScores =  jockeyRepository.fetchJockeyWinRateParStadium(id,raceType,startDate,endDate, range.getMin(), range.getMax());
            List<JockeyWinRateMeanParStadium> stadiumMeanScores = jockeyRepository.fetchJockeyMeanWinRateParStadium(raceType,startDate,endDate, range.getMin(), range.getMax());

            stadiumMeanScores.forEach(stadiumMeanScore -> {
                String stadium = stadiumMeanScore.getStadium();
                JockeyWinRateParStadium targetStadiumScore =  stadiumScores.stream()
                        .filter(stadiumScore -> stadiumScore.getStadium().equals(stadium))
                        .findFirst()
                        .orElse(null);
                if (targetStadiumScore == null){
                    responses.add(
                            GetJockeyWinRateResponse.builder()
                                    .stadium(stadium)
                                    .range(range)
                                    .jockeyWinRates(
                                            JockeyWinRate.builder()
                                                    .winRate(0)
                                                    .count(0)
                                                    .build()
                                    )
                                    .build()
                    );
                } else {
                    float devi = (targetStadiumScore.getJockeyWinRate().getWinRate() - stadiumMeanScore.getMeanWinRate()) / stadiumMeanScore.getStddevdWinRate() * 10 + 50 ;
                    responses.add(
                            GetJockeyWinRateResponse.builder()
                                    .stadium(stadium)
                                    .range(range)
                                    .jockeyWinRates(
                                            JockeyWinRate.builder()
                                                    .winRate(devi)
                                                    .count(targetStadiumScore.getJockeyWinRate().getCount())
                                                    .build()
                                    )
                                    .build()
                    );
                }
            });
        }
        return responses;
    }

    public List<GetJockeyMeanCountResponse> getJockeyMeanCount(RaceType raceType, String raceDate) throws ParseException {
        Date startDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                                DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                        .minusYears(2)
        );
        Date endDate = DateUtils.getDateFormat().parse(raceDate);
        List<GetJockeyMeanCountResponse> responses = new ArrayList<>();

        for (Range range : Range.values()) {
            List<JockeyMeanCount> jockeyMeanCounts = jockeyRepository.fetchJockeyMeanCount(raceType, startDate, endDate, range.getMin(), range.getMax());
            jockeyMeanCounts.forEach(
                    jockeyMeanCount ->
                            responses.add(
                                    GetJockeyMeanCountResponse.builder()
                                            .stadium(jockeyMeanCount.getStadium())
                                            .range(range)
                                            .count(jockeyMeanCount.getCount())
                                            .build()
                            )
            );
        }
        return responses;
    }
}
