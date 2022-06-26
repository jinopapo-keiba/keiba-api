package com.example.demo.contoller;

import com.example.demo.contoller.converter.GetHorseRaceResultResponseConverter;
import com.example.demo.contoller.converter.GetLengthResponseConverter;
import com.example.demo.contoller.converter.GetRaceResponseConverter;
import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.contoller.response.GetHorseRaceResultResponse;
import com.example.demo.contoller.response.GetLengthResponse;
import com.example.demo.contoller.response.GetRaceResponse;
import com.example.demo.converter.RaceConverter;
import com.example.demo.service.RaceService;
import com.example.demo.valueobject.RaceCondition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/race")
@AllArgsConstructor
@RestController
@Slf4j
public class RaceController {
    private RaceService raceService;
    private RaceConverter raceConverter;
    private GetRaceResponseConverter getRaceResponseConverter;
    private GetLengthResponseConverter getLengthResponseConverter;
    private final GetHorseRaceResultResponseConverter getHorseRaceResultResponseConverter;

    @PostMapping
    String saveRace(@RequestBody SaveRaceRequest saveRaceRequest) {
        raceService.saveRace(raceConverter.converter(saveRaceRequest));
        return "success";
    }

    @GetMapping("/before")
    List<GetRaceResponse> getCurrentRace() {
        return raceService.fetchBeforeRace().stream()
                .map(getRaceResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/length")
    GetLengthResponse getLength(String raceId) {
        return getLengthResponseConverter.convert(
                raceService.fetchRaceLength(raceId)
        );
    }

    /**
     * レース情報を取得する
     *
     * @param raceId
     * @return
     */
    @GetMapping("/")
    List<GetRaceResponse> getRace(Integer raceId) {
        return raceService.fetchRace(raceId).stream()
                .map(getRaceResponseConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * 該当レースの直近のレース結果を取得する
     * 検査値は該当レースのグレード内の偏差値
     *
     * @param raceId
     * @return
     */
    @GetMapping("/recent")
    public List<GetHorseRaceResultResponse> getRecentRaceResult(
            Integer raceId,
            String raceConditionParam,
            String stadiumParam,
            Integer raceLengthParam
    ){
        RaceCondition raceCondition = raceConditionParam == null ? RaceCondition.GOOD : RaceCondition.toEnum(raceConditionParam);
        return raceService.fetchHorseRanRecentRace(raceId,raceCondition,stadiumParam,raceLengthParam).stream()
                .map(getHorseRaceResultResponseConverter::converter)
                .collect(Collectors.toList());
    }
}
