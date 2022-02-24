package com.example.demo.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum RaceCondition {
    GOOD("良",0),
    MIDDLE("鞘重",1),
    HEAVY("重",2),
    BAD("不良",3);

    private String text;
    private int value;


    public static RaceCondition toEnum(int value){
        return Arrays.stream(values())
                .filter(v -> v.getValue() == value)
                .findFirst()
                .orElse(null);
    }

    @JsonCreator
    public static RaceCondition toEnum(String text){
        return Arrays.stream(values())
                .filter(v -> Objects.equals(v.getText(), text))
                .findFirst()
                .orElse(null);
    }
}
