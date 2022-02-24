package com.example.demo.entity;

import com.example.demo.valueobject.HorseGender;
import lombok.Value;

@Value
public class Horse {
    String name;
    HorseGender gender;
    int id;
}
