package com.example.demo.service;

import com.example.demo.entity.Race;
import com.example.demo.repository.RaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RaceService {
    private final RaceRepository raceRepository;

    @Transactional
    public void saveRace(Race race){
        raceRepository.saveRace(race);
    }
}
