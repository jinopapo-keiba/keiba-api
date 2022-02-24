package com.example.demo.repository.dto;

import com.example.demo.valueobject.HorseGender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveHorseQueryParam {
    private String name;
    private HorseGender gender;
}
