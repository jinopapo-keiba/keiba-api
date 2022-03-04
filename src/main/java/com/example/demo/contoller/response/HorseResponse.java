package com.example.demo.contoller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HorseResponse {
    int id;
    String name;
    String gender;
}
