package com.example.demo.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JockeyWinRateMeanParRangeStadium {
    private String stadium;
    private Float meanWinRate;
    private Float stddevdWinRate;
}
