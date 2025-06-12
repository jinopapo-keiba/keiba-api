package com.example.demo.contoller;

import com.example.demo.entity.TrainerWinRate;
import com.example.demo.service.TrainerService;
import com.example.demo.valueobject.RaceType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/trainer")
@Tag(name = "Trainer", description = "Operations about trainer")
public class TrainerController {
    private TrainerService trainerService;

    /**
     * 調教師の勝率を取得する
     *
     * @param id trainerId
     * @param raceDate 集計開始日
     * @return 勝率
     */
    @GetMapping("winRate")
    @Operation(summary = "Get trainer win rate", description = "Fetch trainer win rate per stadium")
    Map<String, TrainerWinRate> getTrainerWinRate(int id, String raceType, String raceDate) throws ParseException {
        return trainerService.getTrainerWinRatePerStadium(id, RaceType.toEnum(raceType), raceDate);
    }

    /**
     * 調教師の平均騎乗数を取得する
     *
     * @param raceType
     * @param raceDate
     * @return
     * @throws ParseException
     */
    @GetMapping("meanCount")
    @Operation(summary = "Get trainer mean count", description = "Fetch average riding count")
    Map<String, Float> getTrainerMeanCount(String raceType, String raceDate) throws ParseException {
        return trainerService.getTrainerMeanCount(RaceType.toEnum(raceType),raceDate);
    }

}
