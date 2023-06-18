package com.example.demo.contoller.response.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrainerResponse {
    String name;
    Integer id;
}
