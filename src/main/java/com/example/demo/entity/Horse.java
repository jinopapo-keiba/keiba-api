package com.example.demo.entity;

import com.example.demo.valueobject.HorseGender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Horse {
    Integer id;
    String name;
    HorseGender gender;
}
