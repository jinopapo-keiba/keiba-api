package com.example.demo.contoller.converter

import com.example.demo.contoller.response.GetRaceResponse
import com.example.demo.entity.Race
import com.example.demo.valueobject.RaceType
import spock.lang.Specification

class GetRaceResponseConverterSpec extends Specification {
    def "converterTest"() {
        when:
        Race race = Race.builder()
        .raceName("テストレース")
        .raceType(RaceType.OBSTACLE)
        .raceLength(1200)
        .build()
        GetRaceResponseConverter converter = new GetRaceResponseConverter()
        GetRaceResponse getRaceResponse = converter.convert(race)

        then:
        getRaceResponse.getRaceName() == "テストレース"
        getRaceResponse.getRaceType() == RaceType.OBSTACLE.text
        getRaceResponse.getRaceLength() == 1200
    }
}
