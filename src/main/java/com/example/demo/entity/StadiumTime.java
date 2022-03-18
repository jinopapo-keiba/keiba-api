package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
public class StadiumTime {
    private String stadium;
    private Duration time;
    private Integer count;
}
