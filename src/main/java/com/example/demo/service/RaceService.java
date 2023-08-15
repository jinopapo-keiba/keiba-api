package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.repository.dto.DeviBase;
import com.example.demo.repository.dto.HorseQueryParam;
import com.example.demo.repository.dto.RaceQueryParam;
import com.example.demo.service.dto.RecentHorseResultDto;
import com.example.demo.service.dto.RecentRaceQuery;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.ListUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RaceService {
    private final RaceRepository raceRepository;
    private final HorseRepository horseRepository;
    private final JockeyRepository jockeyRepository;
    private final RaceHorseRepository raceHorseRepository;
    private final RaceResultRepository raceResultRepository;
    private final TrainerRepository trainerRepository;

    @Transactional
    public void saveRace(Race race){
        List<Race> races = raceRepository.fetchRace(
                RaceQueryParam.builder()
                        .stadiums(List.of(race.getStadium()))
                        .round(race.getRound())
                        .beforeRace(true)
                        .raceDate(race.getRaceDate())
                        .build());
        if(!races.isEmpty()) {
            race.setId(races.get(0).getId());
            raceRepository.updateRace(race);
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

        //調教師の保存
        Set<Trainer> trainers = race.getRaceHorses().stream().map(RaceHorse::getTrainer).collect(Collectors.toSet());

        List<Trainer> savedTrainers = trainerRepository.fetchTrainers(
                trainers.stream().map(Trainer::getName).collect(Collectors.toList())
        );
        trainers.stream()
                .filter(trainer -> ListUtils.search(trainer,savedTrainers,(a,b) -> a.getName().equals(b.getName())) == null)
                .forEach(trainerRepository::saveTrainer);

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
        LocalDateTime now = LocalDateTime.now().minusMonths(1);
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
    public List<Race> fetchRace(Integer raceId, Boolean beforeFlag) {
        if (beforeFlag) {
            return raceRepository.fetchRace(RaceQueryParam.builder()
                    .raceId(raceId)
                    .beforeRace(true)
                    .build());
        } else {
            List<Race> race = raceRepository.fetchRace(RaceQueryParam.builder()
                    .raceId(raceId)
                    .beforeRace(true)
                    .build());
            List<Race> raceWithResult = raceRepository.fetchRace(RaceQueryParam.builder()
                    .raceId(raceId)
                    .beforeRace(false)
                    .build());
            race.get(0).setRaceHorses(race.get(0).getRaceHorses().stream()
                    .map(
                            raceHorse -> {
                                Optional<RaceHorse> targetRaceHorse = raceWithResult.get(0).getRaceHorses().stream().filter(
                                        raceHorseWithResult -> raceHorseWithResult.getHorse().getId().equals(raceHorse.getHorse().getId())
                                ).findFirst();
                                return targetRaceHorse.orElse(raceHorse);
                            })
                    .toList());
            return race;
        }
    }

    /**
     * 出走馬の直近のレースを取得
     *
     * @param query 絞り込みパラメーター
     * @return
     */
    public List<RecentHorseResultDto> fetchHorseRanRecentRace(RecentRaceQuery query){
        Race targeRace = raceRepository.fetchRace(RaceQueryParam.builder()
                .raceId(query.getRaceId())
                .beforeRace(true)
                .build()).get(0);
        List<Race> recentRanRaces = raceRepository.fetchRace(
                RaceQueryParam.builder()
                        .horseIds(targeRace.getRaceHorses().stream()
                                .map(raceHorse -> raceHorse.getHorse().getId())
                                .collect(Collectors.toList()))
                        .raceCondition(query.getRaceCondition())
                        .startRaceDate(DateUtils.convertLocalDateTime2Date(LocalDateTime.ofInstant(targeRace.getRaceDate().toInstant(), ZoneId.systemDefault()).minusYears(2)))
                        .endRaceDate(targeRace.getRaceDate())
                        .stadiums(query.getStadiums())
                        .minRaceLength(query.getMinRaceLength())
                        .maxRaceLength(query.getMaxRaceLength())
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
                                        .limit(5)
                                        .map(
                                                race -> {
                                                    DeviBase deviBase = raceRepository.fetchDeviBase(race.getRaceType(),race.getRaceCondition(),race.getStadium(),race.getRaceLength());
                                                    race.setRaceHorses(race.getRaceHorses().stream().map(
                                                            updateRaceHorse -> {
                                                                RaceResult raceResult = updateRaceHorse.getRaceResult();
                                                                raceResult.setMeanFullTime(deviBase.meanFullTime());
                                                                raceResult.setMeanLastRapTime(deviBase.meanLastRapTime());
                                                                raceResult.setStdDeviFullTime(deviBase.stdDeviFullTime());
                                                                raceResult.setStdDeviLastRapTime(deviBase.stdDeviLastRapTime());
                                                                updateRaceHorse.setRaceResult(raceResult);
                                                                return updateRaceHorse;
                                                            })
                                                            .toList()
                                                    );
                                                    return race;
                                                }
                                        )
                                        .collect(Collectors.toList()))
                                .build())
                .collect(Collectors.toList());
    }

    /**
     * 学習用のraceIdの一覧を取得する
     * testFlagがtrueのときは検証用のテストidだけ取得する
     *
     * @param testFlag 検証フラグ
     * @return raceId
     */
    public List<Integer> fetchAllRace(boolean testFlag) {
        if(testFlag) {
            List<Integer> ids = raceRepository.fetchTestGradeRace();
            ids.addAll(raceRepository.fetchTestNoneGradeRace());
            return ids;
        } else {
            return raceRepository.fetchAllRace();
        }
    }
}
