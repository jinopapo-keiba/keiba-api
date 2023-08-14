package com.example.demo.valueobject

import spock.lang.Specification

class RaceConditionSpec extends Specification {
    def "StringToEnum"() {
        when:
        RaceCondition raceCondition = RaceCondition.toEnum(raceConditionText)

        then:
        raceCondition == raceCondition

        where:
        raceConditionText || raceCondition
        "良"              || RaceCondition.GOOD
        "稍重"            || RaceCondition.MIDDLE
        "重"              || RaceCondition.HEAVY
        "不良"            || RaceCondition.BAD
    }

    def "IntegerToEnum"() {
        when:
        RaceCondition raceCondition = RaceCondition.toEnum(value)

        then:
        raceCondition == raceCondition

        where:
        raceConditionText || value
        "良"              || 0
        "稍重"            || 1
        "重"              || 2
        "不良"            || 3
    }
}
