package com.example.demo.valueobject

import spock.lang.Specification

class RaceCondtionSpec extends Specification{
    def "StringToEnum"(){
        when:
        RaceCondition raceCondition = RaceCondition.toEnum("稍重")

        then:
        raceCondition == RaceCondition.MIDDLE
    }
}
