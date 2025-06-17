package com.example.demo.contoller;

import com.example.demo.contoller.response.GetJockeyWinRateResponse;
import com.example.demo.entity.Jockey;
import com.example.demo.entity.JockeyWinRate;
import com.example.demo.repository.JockeyRepository;
import com.example.demo.utils.DateUtils;
import com.example.demo.valueobject.Grade;
import com.example.demo.valueobject.RaceType;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/v1/jockey")
@AllArgsConstructor
@Tag(name = "Jockey", description = "Operations about jockey")
@Hidden
public class JockeyController {
    private JockeyRepository jockeyRepository;

    @GetMapping
    @Operation(summary = "Get jockey", description = "Fetch jockey details by id")
    Jockey getJockey(int id){
        return jockeyRepository.fetchJockey(id);
    }

    /**
     * ジョッキーの平均順位を取得する
     *
     * @param id jockeyId
     * @param raceLength 距離
     * @param stadium　競技場
     * @param raceType レース種別
     * @return 平均順位
     */
    @GetMapping("result")
    @Operation(summary = "Get jockey result", description = "Get jockey average ranking")
    Float getJockeyResult(int id,int raceLength,String stadium,String raceType,String raceDate) throws ParseException {
        Date targetDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                        DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                .minusYears(2)
        );

        Float score =  jockeyRepository.fetchJockeyRanking(id, raceLength, stadium, RaceType.toEnum(raceType),targetDate);
        return score == null ? 0.0f : score;
    }

    /**
     * ジョッキーの勝率を取得する
     *
     * @param id jockeyId
     * @param stadium　競技場
     * @param raceType レース種別
     * @param grade grade
     * @return 勝率
     */
    @GetMapping("winRate")
    @Operation(summary = "Get jockey win rate", description = "Get jockey win rate statistics")
    GetJockeyWinRateResponse getJockeyWinRate(int id, String stadium, String raceType, String raceDate, String grade) throws ParseException {
        Date startDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                                DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                        .minusYears(2)
        );
        Date endDate = DateUtils.getDateFormat().parse(raceDate);

        List<JockeyWinRate> scores = new ArrayList<>();
        JockeyWinRate allScore =  jockeyRepository.fetchJockeyWinRate(id,  null, RaceType.toEnum(null),startDate,endDate, Grade.toEnum(null).getValue());
        JockeyWinRate sameRaceScore =  jockeyRepository.fetchJockeyWinRate(id,  stadium, RaceType.toEnum(raceType),startDate,endDate, Grade.toEnum(null).getValue());
        JockeyWinRate sameGradeScore =  jockeyRepository.fetchJockeyWinRate(id,  null, RaceType.toEnum(null),startDate,endDate, Grade.toEnum(grade).getValue());
        if(sameGradeScore == null){
            sameGradeScore = JockeyWinRate.builder()
                    .count(0)
                    .winRate(0)
                    .build();
        }
        if(sameRaceScore == null){
            sameRaceScore = JockeyWinRate.builder()
                    .count(0)
                    .winRate(0)
                    .build();
        }
        if(allScore == null){
            allScore = JockeyWinRate.builder()
                    .count(0)
                    .winRate(0)
                    .build();
        }
        scores.add(allScore);
        scores.add(sameRaceScore);
        scores.add(sameGradeScore);

        List<Float> meanScores = new ArrayList<>();
        Float allMeanScore =  jockeyRepository.fetchJockeyMeanWinRate( null, RaceType.toEnum(null),startDate,endDate, Grade.toEnum(null).getValue());
        Float sameMeanRaceScore =  jockeyRepository.fetchJockeyMeanWinRate( stadium, RaceType.toEnum(raceType),startDate,endDate, Grade.toEnum(null).getValue());
        Float sameMeanGradeScore =  jockeyRepository.fetchJockeyMeanWinRate(null, RaceType.toEnum(null),startDate,endDate, Grade.toEnum(grade).getValue());

        meanScores.add(allMeanScore);
        meanScores.add(sameMeanRaceScore);
        meanScores.add(sameMeanGradeScore);

        JockeyWinRate allWinRate =  jockeyRepository.fetchJockeyAllWinRate(id,startDate,endDate);
        if(allWinRate == null){
            allWinRate = JockeyWinRate.builder()
                    .count(0)
                    .winRate(0)
                    .build();
        }
        Float meanAllScore = jockeyRepository.fetchJockeyAllMeanWinRate(startDate,endDate);

        return GetJockeyWinRateResponse.builder()
                .jockeyWinRates(scores)
                .jockeyMeanWinRates(meanScores)
                .jockeyAllWinRates(allWinRate)
                .jockeyAllMeanWinRates(meanAllScore)
                .build();
    }
}
