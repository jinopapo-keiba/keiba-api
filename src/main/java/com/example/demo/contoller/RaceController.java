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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Tag(name = "Race", description = "Operations about race")
public class RaceController {
    private RaceService raceService;
    private RaceConverter raceConverter;
    private GetRaceResponseConverter getRaceResponseConverter;
    private StadiumRepository stadiumRepository;
    private final GetHorseRaceResultResponseConverter getHorseRaceResultResponseConverter;

    @PostMapping
    @Operation(summary = "Save race", description = "Save race information")
    String saveRace(@RequestBody SaveRaceRequest saveRaceRequest) {
        raceService.saveRace(raceConverter.converter(saveRaceRequest));
        return "success";
    }

    /**
     * 開催の情報を保存するapi
     *
     */
    @PostMapping("/stadium")
    @Operation(summary = "Save stadium", description = "Save stadium information")
    String saveStadiumInfo(@RequestBody SaveStadiumRequest saveStadiumRequest){
        stadiumRepository.saveStadium(saveStadiumRequest);
        return "success";
    }

    @GetMapping("/before")
    @Operation(summary = "Get current race", description = "Fetch upcoming races")
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
    @Operation(summary = "Get race", description = "Fetch race information")
    List<GetRaceResponse> getRace(
            @Parameter(description = "レースID。YYYYMMDD+開催地+レース番号の組み合わせ", example = "202301010801") Integer raceId,
            @Parameter(description = "開催前の情報を取得するか", example = "true") @RequestParam(required = false,defaultValue = "true") Boolean beforeFlag,
            @Parameter(description = "払戻情報を含めるか", example = "false") @RequestParam(required = false,defaultValue = "false") Boolean payoutFlag) {
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
    @Operation(summary = "Get recent race result", description = "Fetch recent race results before the target race")
    public List<GetHorseRaceResultResponse> getRecentRaceResult(
            @Parameter(description = "対象レースID", example = "202301010801") Integer raceId,
            @Parameter(description = "馬場状態", example = "良",
                    schema = @Schema(allowableValues = {"良","稍重","重","不良"})) String raceCondition,
            @Parameter(description = "対象スタジアム", example = "[\"東京\",\"阪神\"]") @RequestParam(required = false) List<String> stadiums,
            @Parameter(description = "最低距離", example = "1200", minimum = "1000", maximum = "3600") @RequestParam(required = false) Integer minRaceLength,
            @Parameter(description = "最大距離", example = "1800", minimum = "1000", maximum = "3600") @RequestParam(required = false) Integer maxRaceLength
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
    @Operation(summary = "Get all race ids", description = "Fetch all race identifiers")
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
    @Operation(summary = "Get major grade rate", description = "Fetch ratio of major grade races at stadium")
    public Float majorGradeRate(String raceDate,String stadium) throws ParseException {
        return raceService.fetchMajorGradeRate(raceDate,stadium);
    }

    @GetMapping("/id")
    @Operation(summary = "Get race id", description = "Fetch race id from parameters")
    public String raceId(String raceDate, String stadium, Integer round) throws ParseException {
        return raceService.fetchRaceId(raceDate, stadium, round);
    }
}
