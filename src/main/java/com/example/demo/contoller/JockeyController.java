package com.example.demo.contoller;

import com.example.demo.entity.Jockey;
import com.example.demo.repository.JockeyRepository;
import com.example.demo.valueobject.RaceType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    Float getJockeyResult(int id,int raceLength,String stadium,int raceType) {
        return jockeyRepository.fetchJockeyRanking(id, raceLength, stadium, RaceType.toEnum(raceType));
    }
}
