package com.example.demo.contoller;

import com.example.demo.contoller.converter.GetBestRaceTimeResponseConverter;
import com.example.demo.contoller.converter.GetStadiumSummaryResponseConverter;
import com.example.demo.contoller.response.GetBestRaceTimeResponse;
import com.example.demo.contoller.response.GetStadiumSummaryResponse;
import com.example.demo.entity.HorseResult;
import com.example.demo.service.RaceResultService;
import com.example.demo.valueobject.Grade;
import com.example.demo.valueobject.RaceCondition;
import com.example.demo.valueobject.SummaryType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/raceResult")
@RestController
@AllArgsConstructor
public class RaceResultController {
    private final RaceResultService raceResultService;
    private final GetBestRaceTimeResponseConverter getBestRaceTimeResponseConverter;
    private final GetStadiumSummaryResponseConverter getStadiumSummaryResponseConverter;

    @GetMapping("/bestTime")
    public GetBestRaceTimeResponse getBestTime(
            @RequestParam(name="raceLength", required = false) Integer raceLength,
            @RequestParam("raceId") Integer raceId,
            @RequestParam(required = false) List<String> stadiums,
            @RequestParam(name="raceCondition", required = false) String raceConditionParam
            ){
        RaceCondition raceCondition = RaceCondition.toEnum(raceConditionParam);
        return getBestRaceTimeResponseConverter.converter(
                raceResultService.fetchBestRaceTime(stadiums,raceLength,raceId,raceCondition)
        );
    }

    @GetMapping("/stadiumSummary")
    public List<GetStadiumSummaryResponse> getStadiumSummary(
            String time,
            Integer raceLength,
            String grade
    ){
        return raceResultService.fetchStadiumSummaryTimes(time,raceLength, grade == null ? null : Grade.toEnum(grade)).stream()
                .map(getStadiumSummaryResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/horseResult")
    public List<HorseResult> getHorseResult(
     Integer horseId
    ){
        return raceResultService.fetchHorseResult(horseId);
    }
}
