package com.example.demo.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum Grade {
    G3("G3",3),
    G2("G2",2),
    G1("G1",1),
    NONE("",0);
    private String text;
    private int value;

    public static Grade toEnum(int value){
        return Arrays.stream(values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(NONE);
    }

    @JsonCreator
    public static Grade toEnum(String text){
        return Arrays.stream(values())
                .filter(v -> Objects.equals(v.getText(), text))
                .findFirst()
                .orElse(NONE);
    }
}
