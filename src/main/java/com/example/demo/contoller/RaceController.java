package com.example.demo.contoller;

import com.example.demo.contoller.converter.GetHorseRaceResultResponseConverter;
import com.example.demo.contoller.converter.GetRaceResponseConverter;
import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.contoller.request.SaveStadiumRequest;
import com.example.demo.contoller.response.GetHorseRaceResultResponse;
import com.example.demo.contoller.response.GetRaceResponse;
import com.example.demo.converter.RaceConverter;
import com.example.demo.repository.StadiumRepository;
import com.example.demo.service.RaceService;
import com.example.demo.service.dto.RecentRaceQuery;
import com.example.demo.valueobject.RaceCondition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/race")
@AllArgsConstructor
@RestController
@Slf4j
@CrossOrigin
public class RaceController {
    private RaceService raceService;
    private RaceConverter raceConverter;
    private GetRaceResponseConverter getRaceResponseConverter;
    private StadiumRepository stadiumRepository;
    private final GetHorseRaceResultResponseConverter getHorseRaceResultResponseConverter;

    @PostMapping
    String saveRace(@RequestBody SaveRaceRequest saveRaceRequest) {
        raceService.saveRace(raceConverter.converter(saveRaceRequest));
        return "success";
    }

    /**
     * 開催の情報を保存するapi
     *
     */
    @PostMapping("/stadium")
    String saveStadiumInfo(@RequestBody SaveStadiumRequest saveStadiumRequest){
        stadiumRepository.saveStadium(saveStadiumRequest);
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
    List<GetRaceResponse> getRace(Integer raceId,@RequestParam(required = false,defaultValue = "true") Boolean beforeFlag,@RequestParam(required = false,defaultValue = "false") Boolean payoutFlag) {
        return raceService.fetchRace(raceId,beforeFlag,payoutFlag).stream()
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

    @GetMapping("/all")
    public List<Integer> allRace(@RequestParam(required = false, defaultValue = "false") boolean testFlag) {
        return raceService.fetchAllRace(testFlag);
    }

    /**
     *
     * 学習用エントリポイント
     * 該当のスタジアムで重賞が開催されていた比率を正規化された状態で取得する
     *
     * @param raceDate
     * @param stadium
     * @return
     * @throws ParseException
     */
    @GetMapping("/majorGradeRate")
    public Float majorGradeRate(String raceDate,String stadium) throws ParseException {
        return raceService.fetchMajorGradeRate(raceDate,stadium);
    }

    @GetMapping("/id")
    public String raceId(String raceDate, String stadium, Integer round) throws ParseException {
        return raceService.fetchRaceId(raceDate, stadium, round);
    }
}
