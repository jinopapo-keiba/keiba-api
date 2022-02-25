package com.example.demo.service;

import com.example.demo.entity.Horse;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceHorse;
import com.example.demo.repository.HorseRepository;
import com.example.demo.repository.RaceRepository;
import com.example.demo.repository.dto.HorseQueryParam;
import com.example.demo.repository.dto.RaceQueryParam;
import com.example.demo.utils.ListUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RaceService {
    private final RaceRepository raceRepository;
    private final HorseRepository horseRepository;

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
        List<String> jockeyNames = new ArrayList<>();
        race.getRaceHorses().forEach(
                raceHorse -> {
                    jockeyNames.add(raceHorse.getJockey().getName());
                });

        //馬の情報の保存とidの取得
        List<Horse> horses = race.getRaceHorses().stream().map(RaceHorse::getHorse).collect(Collectors.toList());
        List<Horse> savedHorses = horseRepository.fetchHorses(
                HorseQueryParam.builder()
                        .names(horses.stream().map(Horse::getName).collect(Collectors.toList()))
                        .build());
        List<Horse> noSavedHorses = horses.stream()
                .filter(horse -> !savedHorses.contains(horse))
                .collect(Collectors.toList());
        horseRepository.saveHorses(noSavedHorses);
        horses = replaceHorse(horses,savedHorses);
        horses = replaceHorse(horses,noSavedHorses);

    }

    private List<Horse> replaceHorse(List<Horse> targetHorses,List<Horse> resourceHorses){
        return targetHorses.stream()
                .map(targetHorse -> {
                    Horse horse = ListUtils.search(targetHorse,resourceHorses,(t1,t2) -> t1.getName().equals(t2.getName()));
                    if(horse == null){
                        return targetHorse;
                    } else {
                        return horse;
                    }})
                .collect(Collectors.toList());
    }
}
