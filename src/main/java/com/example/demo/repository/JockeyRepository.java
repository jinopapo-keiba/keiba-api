package com.example.demo.repository;

import com.example.demo.entity.JockekMeanCountParRange;
import com.example.demo.entity.JockekMeanCountParStadium;
import com.example.demo.entity.Jockey;
import com.example.demo.entity.JockeyWinRate;
import com.example.demo.repository.dto.*;
import com.example.demo.valueobject.RaceType;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.Date;
import java.util.List;

@Mapper
public interface JockeyRepository {
    Jockey fetchJockey(int id);
    List<Jockey> fetchJockeys(List<String> names);
    void saveJockey(@Param("jockey") Jockey jockey);

    Float fetchJockeyRanking(int id, int raceLength, String stadium, RaceType raceType, Date raceDate);

    @Cacheable("jockeyWinRate")
    JockeyWinRate fetchJockeyWinRate(int id, String stadium, RaceType raceType, Date startDate, Date endDate, int grade);

    @Cacheable("jockeyMeanWinRate")
    Float fetchJockeyMeanWinRate(String stadium, RaceType raceType, Date startDate, Date endDate, int grade);

    @Cacheable("jockeyAllWinRate")
    JockeyWinRate fetchJockeyAllWinRate(int id, Date startDate, Date endDate);

    @Cacheable("jockeyWinRateParRangeStadium")
    List<JockeyWinRateParRangeStadium> fetchJockeyWinRateParRangeStadium(int id, RaceType raceType, Date startDate, Date endDate, int minLength, int maxLength);

    @Cacheable("jockeyAllMeanWinRate")
    Float fetchJockeyAllMeanWinRate(Date startDate, Date endDate);

    @Cacheable("jockeyWinRateParRangeStadium")
    List<JockeyWinRateMeanParRangeStadium> fetchJockeyMeanWinRateParRangeStadium(RaceType raceType, Date startDate, Date endDate, int minLength, int maxLength);

    @Cacheable("jockeyMeanCount")
    @Select(
            "select stadium,AVG(total) as count from (SELECT r.stadium as stadium, r.raceType as raceType, COUNT(*) AS total FROM raceHorse rh JOIN race r ON rh.raceId = r.id JOIN jockey j ON rh.jockeyId = j.id WHERE r.raceDate >= #{startDate} AND r.raceDate < #{endDate} and raceType = #{raceType} and r.raceLength >= #{minLength} AND r.raceLength < #{maxLength} GROUP BY r.stadium, r.raceType,j.id) hoge GROUP BY stadium,raceType order by stadium;"
    )
    List<JockeyMeanCount> fetchJockeyMeanCount(RaceType raceType, Date startDate, Date endDate, int minLength, int maxLength);

    @Cacheable("jockeyWinRateParRange")
    @Select(
            """
                    -- 全体の勝率および出走回数を持つジョッキーのみを対象とするサブクエリ
                    WITH JockeyOverallStats AS (
                        SELECT
                            jockey.id AS JockeyId,
                            COUNT(*) AS count,
                            ROUND(SUM(CASE WHEN raceResult.ranking = 1 THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) AS winRate
                        FROM
                            race
                        JOIN
                            raceHorse ON race.id = raceHorse.raceId
                        JOIN
                            jockey ON raceHorse.jockeyId = jockey.id
                        JOIN
                            raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                        WHERE
                            race.raceDate >= #{startDate}
                        AND
                            race.raceDate < #{endDate}
                        AND
                            raceHorse.jockeyId = #{id}
                        AND
                            raceType in (0,1)
                        GROUP BY
                            jockey.id
                    )
                    -- 全体の勝率
                    SELECT
                        NULL AS raceType,
                        NULL AS raceRange,
                        count,
                        winRate
                    FROM
                        JockeyOverallStats
                    UNION ALL
                    -- レース種別および距離別の勝率
                    SELECT
                        CASE
                            WHEN race.raceType = 0 THEN '芝'
                            WHEN race.raceType = 1 THEN 'ダート'
                        END AS raceType,
                        CASE
                            WHEN race.raceLength < 1400 THEN 'SPRINT'
                            WHEN race.raceLength >= 1400 AND race.raceLength < 1800 THEN 'MILE'
                            WHEN race.raceLength >= 1800 AND race.raceLength < 2400 THEN 'MIDDLE'
                            ELSE 'STAYER'
                        END AS raceRange,
                        COUNT(*) AS count,
                        ROUND(SUM(CASE WHEN raceResult.ranking = 1 THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) AS winRate
                    FROM
                        race
                    JOIN
                        raceHorse ON race.id = raceHorse.raceId
                    JOIN
                        jockey ON raceHorse.jockeyId = jockey.id
                    JOIN
                        raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                    JOIN
                        JockeyOverallStats AS JOS ON jockey.id = JOS.JockeyId
                    WHERE
                        race.raceDate >= #{startDate}
                    AND
                        race.raceDate < #{endDate}
                    AND
                        raceHorse.jockeyId = #{id}
                    AND
                        raceType in (0,1)
                    GROUP BY
                        jockey.id,
                        raceType,
                        raceRange
                    ORDER BY
                        raceType, raceRange;
           """
    )
    List<JockeyWinRateParRangeDto> fetchJockeyWinRateParRange(@Param("id") int id, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Cacheable("jockeyMeanWinRateParRange")
    @Select(
            """
                    -- 全体の勝率および出走回数を持つジョッキーのみを対象とするサブクエリ
                    WITH JockeyOverallStats AS (
                        SELECT
                            jockey.id AS JockeyId,
                            COUNT(*) AS count,
                            ROUND(SUM(CASE WHEN raceResult.ranking = 1 THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) AS winRate
                        FROM
                            race
                        JOIN
                            raceHorse ON race.id = raceHorse.raceId
                        JOIN
                            jockey ON raceHorse.jockeyId = jockey.id
                        JOIN
                            raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                        WHERE
                            race.raceDate >= #{startDate}
                        AND
                            race.raceDate < #{endDate}
                        AND
                            raceType in (0,1)
                        GROUP BY
                            jockey.id
                        HAVING
                            count > 100
                    ),
                    MeanWinRate AS (
                        -- 全体の勝率
                        SELECT
                            NULL AS raceType,
                            NULL AS raceRange,
                            count,
                            winRate
                        FROM
                            JockeyOverallStats
                        UNION ALL
                        -- レース種別および距離別の勝率
                        SELECT
                            CASE
                                WHEN race.raceType = 0 THEN '芝'
                                WHEN race.raceType = 1 THEN 'ダート'
                            END AS raceType,
                            CASE
                                WHEN race.raceLength < 1400 THEN 'SPRINT'
                                WHEN race.raceLength >= 1400 AND race.raceLength < 1800 THEN 'MILE'
                                WHEN race.raceLength >= 1800 AND race.raceLength < 2400 THEN 'MIDDLE'
                                ELSE 'STAYER'
                            END AS raceRange,
                            COUNT(*) AS count,
                            ROUND(SUM(CASE WHEN raceResult.ranking = 1 THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) AS winRate
                        FROM
                            race
                        JOIN
                            raceHorse ON race.id = raceHorse.raceId
                        JOIN
                            jockey ON raceHorse.jockeyId = jockey.id
                        JOIN
                            raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                        JOIN
                            JockeyOverallStats AS JOS ON jockey.id = JOS.JockeyId
                        WHERE
                            race.raceDate >= #{startDate}
                        AND
                            race.raceDate < #{endDate}
                        AND
                            raceType in (0,1)
                        GROUP BY
                            jockey.id,
                            raceType,
                            raceRange
                    )
                    SELECT
                        AVG(winRate) as avg,
                        STDDEV_POP(winRate) AS stddevd,
                        raceType,
                        raceRange
                    FROM
                        MeanWinRate
                    GROUP BY
                        raceType,
                        raceRange;
           """
    )
    List<JockeyMeanWinRateParRangeDto> fetchJockeyMeanWinRateParRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Cacheable("jockeyMeanCountParRange")
    @Select(
            """
               WITH CountRace AS (
                    SELECT
                        NULL AS raceType,
                        NULL AS raceRange,
                        COUNT(*) AS count
                    FROM
                        race
                    JOIN
                        raceHorse ON race.id = raceHorse.raceId
                    JOIN
                        jockey ON raceHorse.jockeyId = jockey.id
                    JOIN
                        raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                    WHERE
                        race.raceDate >= #{startDate}
                    AND
                        race.raceDate < #{endDate}
                    AND
                        raceType in (0,1)
                    GROUP BY
                        jockey.id
                    UNION
                    SELECT
                        CASE
                            WHEN race.raceType = 0 THEN '芝'
                            WHEN race.raceType = 1 THEN 'ダート'
                        END AS raceType,
                        CASE
                            WHEN race.raceLength < 1400 THEN 'SPRINT'
                            WHEN race.raceLength >= 1400 AND race.raceLength < 1800 THEN 'MILE'
                            WHEN race.raceLength >= 1800 AND race.raceLength < 2400 THEN 'MIDDLE'
                            ELSE 'STAYER'
                        END AS raceRange,
                        COUNT(*) AS count
                    FROM
                        race
                    JOIN
                        raceHorse ON race.id = raceHorse.raceId
                    JOIN
                        jockey ON raceHorse.jockeyId = jockey.id
                    JOIN
                        raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                    WHERE
                        race.raceDate >= #{startDate}
                    AND
                        race.raceDate < #{endDate}
                    AND
                        raceType in (0,1)
                    GROUP BY
                        jockey.id,
                        raceType,
                        raceRange
               )
               SELECT
                    raceType,
                    raceRange,
                    AVG(count) AS count
               FROM
                   CountRace
               GROUP BY
                   raceType,
                   raceRange;
            """
    )
    List<JockekMeanCountParRange> fetchJockeyMeanCountParRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Cacheable("jockeyWinRateParStadium")
    @Select(
            """
                -- 全体の勝率および出走回数を持つジョッキーのみを対象とするサブクエリ
                WITH JockeyOverallStats AS (
                    SELECT
                        jockey.id AS JockeyId,
                        COUNT(*) AS count,
                        ROUND(SUM(CASE WHEN raceResult.ranking = 1 THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) AS winRate
                    FROM
                        race
                    JOIN
                        raceHorse ON race.id = raceHorse.raceId
                    JOIN
                        jockey ON raceHorse.jockeyId = jockey.id
                        JOIN
                            raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                        WHERE
                            race.raceDate >= #{startDate}
                        AND
                            race.raceDate < #{endDate}
                        AND
                            raceType in (0,1)
                        GROUP BY
                            jockey.id
                    ),
                    MeanWinRate AS (
                        -- 全体の勝率
                        SELECT
                            NULL AS raceType,
                            NULL AS stadium,
                            count,
                            winRate
                        FROM
                            JockeyOverallStats
                        UNION ALL
                        -- レース種別および距離別の勝率
                        SELECT
                            CASE
                                WHEN race.raceType = 0 THEN '芝'
                                WHEN race.raceType = 1 THEN 'ダート'
                            END AS raceType,
                            stadium,
                            COUNT(*) AS count,
                            ROUND(SUM(CASE WHEN raceResult.ranking = 1 THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) AS winRate
                        FROM
                            race
                        JOIN
                            raceHorse ON race.id = raceHorse.raceId
                        JOIN
                            jockey ON raceHorse.jockeyId = jockey.id
                        JOIN
                            raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                        JOIN
                            JockeyOverallStats AS JOS ON jockey.id = JOS.JockeyId
                        WHERE
                            race.raceDate >= #{startDate}
                        AND
                            race.raceDate < #{endDate}
                        AND
                            raceType in (0,1)
                        GROUP BY
                            jockey.id,
                            raceType,
                            stadium
                    )
                    SELECT
                        AVG(winRate) as avg,
                        STDDEV_POP(winRate) AS stddevd,
                        raceType,
                        stadium
                    FROM
                        MeanWinRate
                    GROUP BY
                        raceType,
                        stadium
                    HAVING
                        COUNT(*) > 50
                """
    )
    List<JockeyMeanWinRateParStadiumDto> fetchJockeyMeanWinRateParStadium(Date startDate, Date endDate);

    @Cacheable("jockeyWinRateParStadium")
    @Select(
            """
                    -- 全体の勝率および出走回数を持つジョッキーのみを対象とするサブクエリ
                    WITH JockeyOverallStats AS (
                        SELECT
                            jockey.id AS JockeyId,
                            COUNT(*) AS count,
                            ROUND(SUM(CASE WHEN raceResult.ranking = 1 THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) AS winRate
                        FROM
                            race
                        JOIN
                            raceHorse ON race.id = raceHorse.raceId
                        JOIN
                            jockey ON raceHorse.jockeyId = jockey.id
                        JOIN
                            raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                        WHERE
                            race.raceDate >= #{startDate}
                        AND
                            race.raceDate < #{endDate}
                        AND
                            raceHorse.jockeyId = #{id}
                        AND
                            raceType in (0,1)
                        GROUP BY
                            jockey.id
                    )
                    -- 全体の勝率
                    SELECT
                        NULL AS raceType,
                        NULL AS stadium,
                        count,
                        winRate
                    FROM
                        JockeyOverallStats
                    UNION ALL
                    -- レース種別および距離別の勝率
                    SELECT
                        CASE
                            WHEN race.raceType = 0 THEN '芝'
                            WHEN race.raceType = 1 THEN 'ダート'
                        END AS raceType,
                        stadium,
                        COUNT(*) AS count,
                        ROUND(SUM(CASE WHEN raceResult.ranking = 1 THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) AS winRate
                    FROM
                        race
                    JOIN
                        raceHorse ON race.id = raceHorse.raceId
                    JOIN
                        jockey ON raceHorse.jockeyId = jockey.id
                    JOIN
                        raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                    JOIN
                        JockeyOverallStats AS JOS ON jockey.id = JOS.JockeyId
                    WHERE
                        race.raceDate >= #{startDate}
                    AND
                        race.raceDate < #{endDate}
                    AND
                        raceHorse.jockeyId = #{id}
                    AND
                        raceType in (0,1)
                    GROUP BY
                        jockey.id,
                        raceType,
                        stadium
                    ORDER BY
                        raceType, stadium;
       """)
    List<JockeyWinRateParStadiumDto> fetchJockeyWinRateParStadium(int id, Date startDate, Date endDate);

    @Cacheable("jockeyMeanCountParStadium")
    @Select(
            """
               WITH CountRace AS (
                    SELECT
                        NULL AS raceType,
                        NULL AS stadium,
                        COUNT(*) AS count
                    FROM
                        race
                    JOIN
                        raceHorse ON race.id = raceHorse.raceId
                    JOIN
                        jockey ON raceHorse.jockeyId = jockey.id
                    JOIN
                        raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                    WHERE
                        race.raceDate >= #{startDate}
                    AND
                        race.raceDate < #{endDate}
                    AND
                        raceType in (0,1)
                    GROUP BY
                        jockey.id
                    UNION
                    SELECT
                        CASE
                            WHEN race.raceType = 0 THEN '芝'
                            WHEN race.raceType = 1 THEN 'ダート'
                        END AS raceType,
                        stadium,
                        COUNT(*) AS count
                    FROM
                        race
                    JOIN
                        raceHorse ON race.id = raceHorse.raceId
                    JOIN
                        jockey ON raceHorse.jockeyId = jockey.id
                    JOIN
                        raceResult ON raceHorse.raceId = raceResult.raceId AND raceHorse.frameNumber = raceResult.frameNumber
                    WHERE
                        race.raceDate >= #{startDate}
                    AND
                        race.raceDate < #{endDate}
                    AND
                        raceType in (0,1)
                    GROUP BY
                        jockey.id,
                        raceType,
                        stadium
               )
               SELECT
                    raceType,
                    stadium,
                    AVG(count) AS count
               FROM
                   CountRace
               GROUP BY
                   raceType,
                   stadium;
            """
    )
    List<JockekMeanCountParStadium> fetchJockeyMeanCountParStadium(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
