package com.example.demo.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Clockwise {
    LEFT("左",0),
    RIGHT("右",1),
    NONE("",-1);
    private String text;
    private int value;


    public static Clockwise toEnum(int value){
        return Arrays.stream(values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(NONE);
    }

    public static Clockwise toEnum(String text){
        return Arrays.stream(values())
                .filter(v -> v.getText().equals(text))
                .findFirst()
                .orElse(NONE);
    }
}
