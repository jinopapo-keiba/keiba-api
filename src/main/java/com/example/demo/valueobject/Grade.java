package com.example.demo.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum Grade {
    G1("G1",0),
    G2("G2",1),
    G3("G3",2),
    LISTED("リステッド",3),
    OPEN("オープン",4),
    WIN3("3勝クラス",5),
    WIN2("2勝クラス",6),
    WIN1("1勝クラス",7),
    NEW("新馬",8),
    WIN0("未勝利",9),
    NONE("",-1);
    private String text;
    private int value;

    public static Grade toEnum(int value){
        return Arrays.stream(values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(NONE);
    }

    public static Grade toEnum(String text){
        return Arrays.stream(values())
                .filter(v -> v.getText().equals(text))
                .findFirst()
                .orElse(NONE);
    }
}
