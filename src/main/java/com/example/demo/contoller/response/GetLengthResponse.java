package com.example.demo.contoller.response;

import com.example.demo.entity.RaceLength;
import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Getter
@Builder
@Schema(description = "距離情報レスポンス")
public class GetLengthResponse {
    List<RaceLength> length;
}
