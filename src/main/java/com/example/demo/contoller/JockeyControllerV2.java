package com.example.demo.contoller;

import com.example.demo.contoller.response.v2.GetJockeyMeanCountResponse;
import com.example.demo.contoller.response.v2.GetJockeyWinRateResponse;
import com.example.demo.entity.JockekMeanCountParRange;
import com.example.demo.entity.JockekMeanCountParStadium;
import com.example.demo.entity.JockeyWinRateParRange;
import com.example.demo.entity.JockeyWinRateParStadium;
import com.example.demo.service.JockeyService;
import com.example.demo.valueobject.RaceType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/v2/jockey")
@AllArgsConstructor
@Tag(name = "Jockey-v2", description = "Operations about jockey v2")
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
    @Operation(summary = "Get jockey win rate v2", description = "Fetch win rate per stadium")
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
    @Operation(summary = "Get jockey mean count", description = "Fetch mean riding count")
    List<GetJockeyMeanCountResponse> getJockeyMeanCount(String raceType, String raceDate) throws ParseException {
        return jockeyService.getJockeyMeanCount(RaceType.toEnum(raceType),raceDate);
    }

    @GetMapping("range/winRate")
    @Operation(summary = "Get win rate by range", description = "Fetch win rate by range")
    List<JockeyWinRateParRange> getJockeyWinRateByRange(int id, String raceDate) throws ParseException {
        return jockeyService.fetchJockeyWinRateParRange(id,raceDate);
    }

    @GetMapping("range/meanCount")
    @Operation(summary = "Get mean count by range", description = "Fetch mean count by range")
    List<JockekMeanCountParRange> getJockeyMeanCountByRange(String raceDate) throws ParseException {
        return jockeyService.fetchJockeyMeanCountParRange(raceDate);
    }

    @GetMapping("stadium/winRate")
    @Operation(summary = "Get win rate by stadium", description = "Fetch win rate by stadium")
    List<JockeyWinRateParStadium> getJockeyWinRateByStadium(int id, String raceDate) throws ParseException {
        return jockeyService.fetchJockeyWinRateParStadium(id,raceDate);
    }

    @GetMapping("stadium/meanCount")
    @Operation(summary = "Get mean count by stadium", description = "Fetch mean count by stadium")
    List<JockekMeanCountParStadium> getJockeyMeanCountByStadium(String raceDate) throws ParseException {
        return jockeyService.fetchJockeyMeanCountParStadium(raceDate);
    }

}