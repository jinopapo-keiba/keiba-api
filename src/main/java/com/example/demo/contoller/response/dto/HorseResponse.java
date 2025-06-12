package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "馬レスポンス")
public class HorseResponse {
    int id;
    String name;
    String gender;
}
