package com.example.demo.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Range {
    SPRINT(0,1400),
    MILE(1400,1800),
    MIDDLE(1800,2400),
    STAYER(2400,9999);
    private int min;
    private int max;
}
