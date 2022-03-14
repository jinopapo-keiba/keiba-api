package com.example.demo.service;

import com.example.demo.repository.StadiumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StadiumService {
    private StadiumRepository stadiumRepository;

    public List<String> fetchRanStadium(String raceId,Integer raceLength) {
       return stadiumRepository.fetchRanStadium(raceId,raceLength);
    }
}
