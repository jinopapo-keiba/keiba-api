package com.example.demo.contoller;

import com.example.demo.contoller.response.GetTrainerWinRateResponse;
import com.example.demo.entity.TrainerWinRate;
import com.example.demo.repository.dto.TrainerWinRateMeanParStadium;
import com.example.demo.repository.dto.TrainerWinRateParStadium;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/trainer")
public class TrainerController {
    private TrainerRepository trainerRepository;

    /**
     * 調教師の勝率を取得する
     *
     * @param id trainerId
     * @param raceDate 集計開始日
     * @return 勝率
     */
    @GetMapping("winRate")
    GetTrainerWinRateResponse getTrainerWinRate(int id, String raceDate) throws ParseException {
        Date startDate = DateUtils.convertLocalDateTime2Date(
                LocalDateTime.ofInstant(
                                DateUtils.getDateFormat().parse(raceDate).toInstant(), ZoneId.systemDefault())
                        .minusYears(2)
        );
        Date endDate = DateUtils.getDateFormat().parse(raceDate);

        TrainerWinRate allScore =  trainerRepository.fetchTrainerWinRate(id,startDate,endDate);
        if(allScore == null){
            allScore = TrainerWinRate.builder()
                    .count(0)
                    .winRate(0)
                    .build();
        }
        Float meanAllScore = trainerRepository.fetchTrainerMeanWinRate(startDate,endDate);
        List<TrainerWinRateParStadium> stadiumScore =  trainerRepository.fetchTrainerWinRateParStadium(id,startDate,endDate);
        List<TrainerWinRateMeanParStadium> stadiumMeanScore = trainerRepository.fetchTrainerMeanWinRateParStadium(startDate,endDate);
        Map<String,Float> stadiumMeanMap = new HashMap<>();
        Map<String,TrainerWinRate> stadiumMap = new HashMap<>();
        for (int i = 0; i < stadiumScore.size(); i++) {
            stadiumMeanMap.put(stadiumMeanScore.get(i).getStadium(),stadiumMeanScore.get(i).getTrainerWinRate());
            stadiumMap.put(stadiumScore.get(i).getStadium(),stadiumScore.get(i).getTrainerWinRate());
        }

        return GetTrainerWinRateResponse.builder()
                .trainerAllWinRates(allScore)
                .trainerAllMeanWinRates(meanAllScore)
                .trainerAllWinRatesParStadium(stadiumMap)
                .trainerAllWinRatesMeanParStadium(stadiumMeanMap)
                .build();
    }

}
