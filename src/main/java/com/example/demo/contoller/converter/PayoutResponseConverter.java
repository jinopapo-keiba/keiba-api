package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.dto.PayoutResponse;
import com.example.demo.entity.Payout;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PayoutResponseConverter {
    public PayoutResponse convert(Payout payout) {
        return PayoutResponse.builder()
                .betType(payout.getBetType().getText())
                .frameNumber(payout.getFrameNumber())
                .payout(payout.getPayout())
                .build();
    }
}
