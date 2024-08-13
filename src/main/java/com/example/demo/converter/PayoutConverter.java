package com.example.demo.converter;

import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.entity.Payout;
import com.example.demo.valueobject.BetType;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PayoutConverter {
    @SneakyThrows
    public Payout converter(SaveRaceRequest.Payout payout) {
        return Payout.builder()
                .betType(BetType.toEnum(payout.getBetType()))
                .frameNumber(payout.getFrameNumber())
                .payout(payout.getPayout())
                .build();
    }
}
