package com.example.demo.repository.dto;

import com.example.demo.entity.JockeyWinRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JockeyWinRateParStadium {
    private String stadium;
    private JockeyWinRate jockeyWinRate;
}
