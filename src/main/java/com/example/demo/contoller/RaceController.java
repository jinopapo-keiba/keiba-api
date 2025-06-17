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
import io.swagger.v3.oas.annotations.Hidden;
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

    @Hidden
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
    @Hidden
    @PostMapping("/stadium")
    @Operation(summary = "Save stadium", description = "Save stadium information")
    String saveStadiumInfo(@RequestBody SaveStadiumRequest saveStadiumRequest){
        stadiumRepository.saveStadium(saveStadiumRequest);
        return "success";
    }

    @Hidden
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
            @Parameter(description = "レースID", example = "1") Integer raceId,
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
    @Operation(summary = "Get recent race result", description = "指定したレースに出走予定の馬の直近6レースの結果を取得する")
    public List<GetHorseRaceResultResponse> getRecentRaceResult(
            @Parameter(description = "対象レースID", example = "202301010801") Integer raceId,
            @Parameter(description = "直近6レースを馬場状態で絞り込む", example = "良",
                    schema = @Schema(allowableValues = {"良","稍重","重","不良"})) @RequestParam(required = false) String raceCondition,
            @Parameter(description = "直近6レースを対象スタジアムで絞り込む", example = "[\"東京\",\"阪神\"]") @RequestParam(required = false) List<String> stadiums,
            @Parameter(description = "直近6レースを最低距離で絞り込む", example = "1200") @RequestParam(required = false) Integer minRaceLength,
            @Parameter(description = "直近6レースを最大距離で絞り込む", example = "1800") @RequestParam(required = false) Integer maxRaceLength
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
    @Operation(summary = "Get all race ids", description = "学習用のデータセットのもととなるidのリストを返す。testFlagがついてるときはバリデーション用のデータセットとして直近1年分を返す")
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
    @Hidden
    @GetMapping("/majorGradeRate")
    @Operation(summary = "Get major grade rate", description = "Fetch ratio of major grade races at stadium")
    public Float majorGradeRate(String raceDate,String stadium) throws ParseException {
        return raceService.fetchMajorGradeRate(raceDate,stadium);
    }

    @Hidden
    @GetMapping("/id")
    @Operation(summary = "Get race id", description = "Fetch race id from parameters")
    public String raceId(String raceDate, String stadium, Integer round) throws ParseException {
        return raceService.fetchRaceId(raceDate, stadium, round);
    }
}
