package com.example.demo.service;

import com.example.demo.entity.Horse;
import com.example.demo.entity.Jockey;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import com.example.demo.repository.HorseRepository;
import com.example.demo.repository.JockeyRepository;
import com.example.demo.repository.RaceHorseRepository;
import com.example.demo.repository.RaceRepository;
import com.example.demo.repository.dto.HorseQueryParam;
import com.example.demo.repository.dto.RaceQueryParam;
import com.example.demo.utils.ListUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RaceService {
    private final RaceRepository raceRepository;
    private final HorseRepository horseRepository;
    private final JockeyRepository jockeyRepository;
    private final RaceHorseRepository raceHorseRepository;

    @Transactional
    public void saveRace(Race race){
        List<Race> races = raceRepository.fetchRace(
                RaceQueryParam.builder()
                        .raceName(race.getRaceName())
                        .raceDate(race.getRaceDate())
                        .build());
        if(!races.isEmpty()) {
            return;
        }
        raceRepository.saveRace(race);

        //馬の情報の保存
        List<Horse> horses = race.getRaceHorses().stream().map(RaceHorse::getHorse).collect(Collectors.toList());
        List<Horse> savedHorses = horseRepository.fetchHorses(
                HorseQueryParam.builder()
                        .names(horses.stream().map(Horse::getName).collect(Collectors.toList()))
                        .build());
        horses.stream()
                .filter(horse -> ListUtils.search(horse,savedHorses,(a,b) -> a.getName().equals(b.getName())) != null)
                .forEach(horseRepository::saveHorse);

        //ジョッキーの保存
        List<Jockey> jockeys = race.getRaceHorses().stream().map(RaceHorse::getJockey).collect(Collectors.toList());
        List<Jockey> savedJockey = jockeyRepository.fetchJockeys(
                jockeys.stream().map(Jockey::getName).collect(Collectors.toList())
        );
        jockeys.stream()
                .filter(jockey -> ListUtils.search(jockey,savedJockey,(a,b) -> a.getName().equals(b.getName())) != null)
                .forEach(jockeyRepository::saveJockey);

        //レースの馬情報の保存
        race.getRaceHorses().forEach(
                raceHorse -> raceHorseRepository.saveRaceHorse(raceHorse,race)
        );
    }
}
