package com.example.demo.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum RaceWeather {
    SUNNY("晴",0),
    CLOUDY("曇",1),
    RAIN("雨",2);
    private String text;
    private int value;

    public static RaceWeather toEnum(int value){
        return Arrays.stream(values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(null);
    }

    public static RaceWeather toEnum(String text){
        return Arrays.stream(values())
                .filter(v -> Objects.equals(v.getText(), text))
                .findFirst()
                .orElse(null);
    }
}
