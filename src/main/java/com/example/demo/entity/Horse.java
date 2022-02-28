package com.example.demo.entity;

import com.example.demo.valueobject.HorseGender;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Horse {
    String name;
    HorseGender gender;
    int id;
}
