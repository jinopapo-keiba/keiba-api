package com.example.demo.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum BetType {
    HUKUSHOU("複勝",1),
    WIDE("ワイド",2),
    WAKUREN("枠連",3),
    UMAREN("馬連",4),
    SANRENPUKU("3連複",5),
    UMATAN("馬単",6),
    SANRENTAN("3連単",7),

    NONE("",-1);

    private String text;
    private int value;

    public static BetType toEnum(int value){
        return Arrays.stream(values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(NONE);
    }

    public static BetType toEnum(String text){
        return Arrays.stream(values())
                .filter(v -> v.getText().equals(text))
                .findFirst()
                .orElse(NONE);
    }
}
