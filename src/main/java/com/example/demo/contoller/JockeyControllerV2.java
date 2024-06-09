package com.example.demo.contoller;

import com.example.demo.contoller.response.v2.GetJockeyMeanCountResponse;
import com.example.demo.contoller.response.v2.GetJockeyWinRateResponse;
import com.example.demo.entity.JockekMeanCountParRange;
import com.example.demo.entity.JockeyWinRateParRange;
import com.example.demo.service.JockeyService;
import com.example.demo.valueobject.RaceType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/v2/jockey")
@AllArgsConstructor
public class JockeyControllerV2 {
    private JockeyService jockeyService;

    /**
     * ジョッキーの勝率の偏差値を取得する
     *
     * @param id jockeyId
     * @param raceType レース種別
     * @param raceDate レース開催日
     *
     * @return 勝率
     */
    @GetMapping("winRate")
    List<GetJockeyWinRateResponse> getJockeyWinRate(int id, String raceType, String raceDate) throws ParseException {
        return jockeyService.getJockeyWinRatePerStadium(id, RaceType.toEnum(raceType), raceDate);
    }

    /**
     * jockeyの平均騎乗数を取得する
     *
     * @param raceType
     * @param raceDate
     * @return
     * @throws ParseException
     */
    @GetMapping("meanCount")
    List<GetJockeyMeanCountResponse> getJockeyMeanCount(String raceType, String raceDate) throws ParseException {
        return jockeyService.getJockeyMeanCount(RaceType.toEnum(raceType),raceDate);
    }

    @GetMapping("range/winRate")
    List<JockeyWinRateParRange> getJockeyWinRateByRange(int id, String raceDate) throws ParseException {
        return jockeyService.fetchJockeyWinRateParRange(id,raceDate);
    }

    @GetMapping("range/meanCount")
    List<JockekMeanCountParRange> getJockeyMeanCountByRange(String raceDate) throws ParseException {
        return jockeyService.fetchJockeyMeanCountParRange(raceDate);
    }
}