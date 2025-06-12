package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "調教師レスポンス")
public class TrainerResponse {
    String name;
    Integer id;
}
