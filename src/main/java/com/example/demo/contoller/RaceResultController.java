package com.example.demo.contoller;

import com.example.demo.contoller.converter.GetStadiumSummaryResponseConverter;
import com.example.demo.contoller.response.GetStadiumSummaryResponse;
import com.example.demo.service.RaceResultService;
import com.example.demo.valueobject.Grade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/raceResult")
@RestController
@AllArgsConstructor
public class RaceResultController {
    private final RaceResultService raceResultService;
    private final GetStadiumSummaryResponseConverter getStadiumSummaryResponseConverter;

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
}
