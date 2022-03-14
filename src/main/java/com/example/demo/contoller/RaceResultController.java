package com.example.demo.contoller;

import com.example.demo.contoller.converter.GetBestRaceTimeResponseConverter;
import com.example.demo.contoller.response.GetBestRaceTimeResponse;
import com.example.demo.service.RaceResultService;
import com.example.demo.valueobject.SummaryType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/raceResult")
@RestController
@AllArgsConstructor
public class RaceResultController {
    private final RaceResultService raceResultService;
    private final GetBestRaceTimeResponseConverter getBestRaceTimeResponseConverter;

    @GetMapping("/bestTime")
    public GetBestRaceTimeResponse getBestTime(
            @RequestParam("raceLength") Integer raceLength,
            @RequestParam("raceId") String raceId,
            String stadium,
            SummaryType summaryType
            ){
        return getBestRaceTimeResponseConverter.converter(
                raceResultService.fetchBestRaceTime(stadium,raceLength,raceId,summaryType)
        );
    }
}
