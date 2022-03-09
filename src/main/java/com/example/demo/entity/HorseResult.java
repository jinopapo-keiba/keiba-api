package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HorseResult {
    Horse horse;
    List<Result> results;
}
