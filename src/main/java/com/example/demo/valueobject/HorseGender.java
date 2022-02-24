package com.example.demo.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum HorseGender {
    LADY("牝",0),
    MAN("牡",1),
    NONE("",-1);
    private String text;
    private int value;

    public static HorseGender toEnum(int value){
        return Arrays.stream(values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(NONE);
    }

    @JsonCreator
    public static HorseGender toEnum(String text){
        return Arrays.stream(values())
                .filter(v -> Objects.equals(v.getText(), text))
                .findFirst()
                .orElse(NONE);
    }
}
