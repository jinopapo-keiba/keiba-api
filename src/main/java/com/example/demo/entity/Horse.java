package com.example.demo.entity;

import com.example.demo.valueobject.HorseGender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Horse {
    String name;
    HorseGender gender;
}
