package com.example.demo.repository.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HorseQueryParam {
    List<String> names;
}
