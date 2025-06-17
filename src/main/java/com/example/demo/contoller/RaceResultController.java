package com.example.demo.contoller;

import com.example.demo.contoller.converter.GetStadiumSummaryResponseConverter;
import com.example.demo.contoller.response.GetStadiumSummaryResponse;
import com.example.demo.service.RaceResultService;
import com.example.demo.valueobject.Grade;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/raceResult")
@RestController
@AllArgsConstructor
@Hidden
@Tag(name = "RaceResult", description = "Operations about race result")
public class RaceResultController {
    private final RaceResultService raceResultService;
    private final GetStadiumSummaryResponseConverter getStadiumSummaryResponseConverter;

    @GetMapping("/stadiumSummary")
    @Operation(summary = "Get stadium summary", description = "Fetch summary of stadium results")
    public List<GetStadiumSummaryResponse> getStadiumSummary(
            String time,
            Integer raceLength,
            String grade
    ){
        return raceResultService.fetchStadiumSummaryTimes(time,raceLength, grade == null ? null : Grade.toEnum(grade)).stream()
                .map(getStadiumSummaryResponseConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * 学習用
     * 勝ち馬を人気別に集計した結果を取得する
     *
     * @return 集計データ
     */
    @GetMapping("/winHorsePopular")
    @Operation(summary = "Get win horse popularity", description = "Fetch count of winning horse popularity")
    public List<Integer> getWinHorsePopular() {
        return raceResultService.fetchWinHorsePopular();
    }
}
