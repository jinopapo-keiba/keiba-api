package com.example.demo.contoller;

import com.example.demo.entity.Jockey;
import com.example.demo.repository.JockeyRepository;
import com.example.demo.utils.DateUtils;
import com.example.demo.valueobject.RaceType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping("/v1/jockey")
@AllArgsConstructor
public class JockeyController {
    private JockeyRepository jockeyRepository;

    @GetMapping
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
    Float getJockeyResult(int id,int raceLength,String stadium,String raceType,String raceDate) throws ParseException {
        Date targetDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                        DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                .minusYears(2)
        );

        Float score =  jockeyRepository.fetchJockeyRanking(id, raceLength, stadium, RaceType.toEnum(raceType),targetDate);
        return score == null ? 0.0f : score;
    }
}
