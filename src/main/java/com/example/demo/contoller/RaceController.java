package com.example.demo.contoller;

import com.example.demo.contoller.converter.GetHorseRaceResultResponseConverter;
import com.example.demo.contoller.converter.GetRaceResponseConverter;
import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.contoller.response.GetHorseRaceResultResponse;
import com.example.demo.contoller.response.GetRaceResponse;
import com.example.demo.converter.RaceConverter;
import com.example.demo.entity.HorseScore;
import com.example.demo.service.RaceService;
import com.example.demo.service.ScoreService;
import com.example.demo.service.dto.RecentRaceQuery;
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
    private ScoreService scoreService;
    private RaceConverter raceConverter;
    private GetRaceResponseConverter getRaceResponseConverter;
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

    /**
     * レース情報を取得する
     *
     * @param raceId
     * @return
     */
    @GetMapping
    List<GetRaceResponse> getRace(Integer raceId,@RequestParam(required = false,defaultValue = "true") Boolean beforeFlag) {
        return raceService.fetchRace(raceId,beforeFlag).stream()
                .map(getRaceResponseConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * 該当の出走前のレースの直近のレース結果を取得する
     * 検査値は該当レースのグレード内の偏差値
     *
     * @param raceId
     * @return
     */
    @GetMapping("/recent")
    public List<GetHorseRaceResultResponse> getRecentRaceResult(
            Integer raceId,
            String raceCondition,
            @RequestParam(required = false) List<String> stadiums,
            @RequestParam(required = false) Integer minRaceLength,
            @RequestParam(required = false) Integer maxRaceLength
    ){
        RecentRaceQuery query = RecentRaceQuery.builder()
                .raceId(raceId)
                .raceCondition(RaceCondition.toEnum(raceCondition))
                .stadiums(stadiums)
                .minRaceLength(minRaceLength)
                .maxRaceLength(maxRaceLength)
                .build();
        return raceService.fetchHorseRanRecentRace(query).stream()
                .map(getHorseRaceResultResponseConverter::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/score")
    public List<HorseScore> calcScore(Integer raceId) {
        return scoreService.calcScore(raceId);
    }

    @GetMapping("/all")
    public List<Integer> allRace() {
        return raceService.fetchAllRace();
    }
}
