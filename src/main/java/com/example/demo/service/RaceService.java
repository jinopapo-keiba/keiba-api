package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.repository.dto.HorseQueryParam;
import com.example.demo.repository.dto.RaceQueryParam;
import com.example.demo.service.dto.RecentHorseResultDto;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.ListUtils;
import com.example.demo.valueobject.RaceCondition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RaceService {
    private final RaceRepository raceRepository;
    private final HorseRepository horseRepository;
    private final JockeyRepository jockeyRepository;
    private final RaceHorseRepository raceHorseRepository;
    private final RaceResultRepository raceResultRepository;

    @Transactional
    public void saveRace(Race race){
        List<Race> races = raceRepository.fetchRace(
                RaceQueryParam.builder()
                        .stadium(race.getStadium())
                        .round(race.getRound())
                        .raceDate(race.getRaceDate())
                        .build());
        if(!races.isEmpty()) {
            raceRepository.updateRace(races.get(0));
            race.setId(races.get(0).getId());
        } else {
            raceRepository.saveRace(race);
        }
        //馬の情報の保存
        List<Horse> horses = race.getRaceHorses().stream().map(RaceHorse::getHorse).collect(Collectors.toList());
        List<Horse> savedHorses = horseRepository.fetchHorses(
                HorseQueryParam.builder()
                        .names(horses.stream().map(Horse::getName).collect(Collectors.toList()))
                        .build());
        horses.stream()
                .filter(horse -> ListUtils.search(horse,savedHorses,(a,b) -> a.getName().equals(b.getName())) == null)
                .forEach(horseRepository::saveHorse);

        //ジョッキーの保存
        List<Jockey> jockeys = race.getRaceHorses().stream().map(RaceHorse::getJockey).collect(Collectors.toList());
        List<Jockey> savedJockey = jockeyRepository.fetchJockeys(
                jockeys.stream().map(Jockey::getName).collect(Collectors.toList())
        );
        jockeys.stream()
                .filter(jockey -> ListUtils.search(jockey,savedJockey,(a,b) -> a.getName().equals(b.getName())) == null)
                .forEach(jockeyRepository::saveJockey);

        //レースの馬情報の保存
        race.getRaceHorses().forEach(
                raceHorse -> raceHorseRepository.saveRaceHorse(raceHorse,race)
        );

        //レース結果の情報保存
        //予想するレースはまだ結果が出てないので、それの場合は何もしない
        race.getRaceHorses().stream()
                .filter(raceHorse -> raceHorse.getRaceResult() != null)
                .forEach(raceHorse -> raceResultRepository.saveRaceResult(raceHorse.getRaceResult(),race,raceHorse)
                );
    }

    /**
     * 結果が出てないレースを取得
     *
     * @return
     */
    public List<Race> fetchBeforeRace(){
        LocalDateTime now = LocalDateTime.now();
        return raceRepository.fetchRace(
                RaceQueryParam.builder()
                        .beforeRace(true)
                        .startRaceDate(
                                DateUtils.convertLocalDateTime2Date(
                                        LocalDateTime.of(
                                                now.getYear(),
                                                now.getMonth(),
                                                now.getDayOfMonth(),
                                                0,
                                                0,
                                                0
                                        )
                                )
                        )
                        .build());
    }

    /**
     * レース情報を取得
     *
     * @param raceId レースid
     * @return レース
     */
    public List<Race> fetchRace(Integer raceId) {
        return raceRepository.fetchRace(RaceQueryParam.builder()
                .raceId(raceId)
                .beforeRace(true)
                .build());
    }

    /**
     * 出走馬の直近のレースを取得
     *
     * @param raceId
     * @param raceCondition
     * @return
     */
    public List<RecentHorseResultDto> fetchHorseRanRecentRace(
            Integer raceId, RaceCondition raceCondition, String stadium, Integer raceLength){
        Race targeRace = raceRepository.fetchRace(RaceQueryParam.builder()
                .raceId(raceId)
                .beforeRace(true)
                .build()).get(0);
        List<Race> recentRanRaces = raceRepository.fetchRace(
                RaceQueryParam.builder()
                        .horseIds(targeRace.getRaceHorses().stream()
                                .map(raceHorse -> raceHorse.getHorse().getId())
                                .collect(Collectors.toList()))
                        .raceCondition(raceCondition)
                        .startRaceDate(DateUtils.convertLocalDateTime2Date(LocalDateTime.now().minusYears(2)))
                        .stadium(stadium)
                        .raceLength(raceLength)
                        .build()
        );
        return targeRace.getRaceHorses().stream()
                .map(
                        raceHorse -> RecentHorseResultDto.builder()
                                .raceHorse(raceHorse)
                                .races(recentRanRaces.stream()
                                        .filter(
                                            race -> race.getRaceHorses().stream()
                                                .anyMatch(horse -> Objects.equals(raceHorse.getHorse().getId(),horse.getHorse().getId()))
                                        )
                                        .collect(Collectors.toList()))
                                .build())
                .collect(Collectors.toList());
    }

    /**
     * 該当レースの出走馬が走ったことのあるレースの距離一覧を取得
     *
     * @param raceId raceid
     * @return 出走経験のある距離
     */
    public List<RaceLength> fetchRaceLength(String raceId) {
        return raceRepository.fetchRanRaceLength(raceId);
    }
}
