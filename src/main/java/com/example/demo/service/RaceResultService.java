package com.example.demo.service;

import com.example.demo.entity.StadiumTime;
import com.example.demo.repository.RaceResultRepository;
import com.example.demo.valueobject.Grade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RaceResultService {
    private final RaceResultRepository raceResultRepository;

    public List<StadiumTime> fetchStadiumSummaryTimes(String time,Integer raceLength, Grade grade) {
       return raceResultRepository.fetchStadiumSummaryTimes(time,raceLength,grade);
    }

    /**
     * 勝ち馬を人気別に集計した結果を取得する
     *
     * @return 集計データ
     */
    public List<Integer> fetchWinHorsePopular() {
        return raceResultRepository.fetchWinHorsePopular();
    }
}
