package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Builder
@Schema(description = "騎手レスポンス")
public class JockeyResponse {
    String name;
    Integer id;
}
