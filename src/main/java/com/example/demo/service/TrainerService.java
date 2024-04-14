package com.example.demo.service;

import com.example.demo.entity.TrainerWinRate;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.dto.TrainerMeanCount;
import com.example.demo.repository.dto.TrainerWinRateMeanParStadium;
import com.example.demo.repository.dto.TrainerWinRateParStadium;
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
public class TrainerService {
    private TrainerRepository trainerRepository;

    public Map<String, TrainerWinRate> getTrainerWinRatePerStadium(int id, RaceType raceType, String raceDate) throws ParseException {
        Date startDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                                DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                        .minusYears(2)
        );
        Date endDate = DateUtils.getDateFormat().parse(raceDate);

        List<TrainerWinRateParStadium> stadiumScores =  trainerRepository.fetchTrainerWinRateParStadium(id,raceType,startDate,endDate);
        List<TrainerWinRateMeanParStadium> stadiumMeanScores = trainerRepository.fetchTrainerMeanWinRateParStadium(raceType,startDate,endDate);
        Map<String, TrainerWinRate> stadiumMap = new HashMap<>();

        stadiumMeanScores.forEach(stadiumMeanScore -> {
            String stadium = stadiumMeanScore.getStadium();
            TrainerWinRateParStadium targetStadiumScore =  stadiumScores.stream()
                    .filter(stadiumScore -> stadiumScore.getStadium().equals(stadium))
                    .findFirst()
                    .orElse(null);
            if (targetStadiumScore == null){
                stadiumMap.put(stadium,
                        TrainerWinRate.builder()
                                .winRate(0)
                                .count(0)
                                .build());
            } else {
                float devi = (targetStadiumScore.getTrainerWinRate().getWinRate() - stadiumMeanScore.getMeanWinRate()) / stadiumMeanScore.getStddevdWinRate() * 10 + 50 ;
                stadiumMap.put(stadium,
                        TrainerWinRate.builder()
                                .winRate(devi)
                                .count(targetStadiumScore.getTrainerWinRate().getCount())
                                .build());
            }
        });

        return stadiumMap;
    }

    public Map<String, Float> getTrainerMeanCount(RaceType raceType, String raceDate) throws ParseException {
        Date startDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                                DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                        .minusYears(2)
        );
        Date endDate = DateUtils.getDateFormat().parse(raceDate);
        List<TrainerMeanCount> trainerMeanCounts = trainerRepository.fetchTrainerMeanCount(raceType,startDate,endDate);
        Map<String,Float> map = new HashMap<>();
        trainerMeanCounts.forEach(
                trainerMeanCount -> map.put(trainerMeanCount.getStadium(), trainerMeanCount.getCount())
        );
        return map;
    }
}
