package com.example.demo.entity;

import com.example.demo.valueobject.BetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payout {
    String frameNumber;
    Float payout;
    BetType betType;
}
