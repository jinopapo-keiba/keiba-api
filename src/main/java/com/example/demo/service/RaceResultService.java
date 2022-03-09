package com.example.demo.service;

import com.example.demo.entity.BestRaceTime;
import com.example.demo.repository.RaceResultRepository;
import com.example.demo.valueobject.SummaryType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RaceResultService {
    private final RaceResultRepository raceResultRepository;

    public List<BestRaceTime> fetchBestRaceTime(String stadium, Integer raceLength, String raceId, SummaryType summaryType){
        return raceResultRepository.fetchBestRaceTimes(stadium,raceLength,raceId,summaryType);
    }
}
