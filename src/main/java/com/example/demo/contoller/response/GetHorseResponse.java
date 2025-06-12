package com.example.demo.contoller.response;

import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Builder
@Data
@Schema(description = "馬情報レスポンス")
public class GetHorseResponse {
    String name;
    String gender;
}
