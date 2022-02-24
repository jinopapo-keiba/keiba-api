package com.example.demo.valueobject;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum RaceType {
    TURF("芝",0),
    DIRT("ダート",1);

    private String text;
    private int value;


    public static RaceType toEnum(int value){
        return Arrays.stream(values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(null);
    }

    public static RaceType toEnum(String text){
        return Arrays.stream(values())
                .filter(v -> Objects.equals(v.getText(), text))
                .findFirst()
                .orElse(null);
    }
}
