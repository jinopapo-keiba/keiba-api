package com.example.demo.contoller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetHorseResponse {
    String name;
    String gender;
}
