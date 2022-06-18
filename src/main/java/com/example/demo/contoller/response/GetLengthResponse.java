package com.example.demo.contoller.response;

import com.example.demo.entity.RaceLength;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetLengthResponse {
    List<RaceLength> length;
}
