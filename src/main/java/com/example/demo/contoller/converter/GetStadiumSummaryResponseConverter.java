package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.GetStadiumSummaryResponse;
import com.example.demo.entity.StadiumTime;
import org.springframework.stereotype.Component;

@Component
public class GetStadiumSummaryResponseConverter {
    public GetStadiumSummaryResponse convert(StadiumTime stadiumTime){
        return GetStadiumSummaryResponse.builder()
                .name(stadiumTime.getStadium())
                .time(stadiumTime.getTime() == null ? 0 : stadiumTime.getTime().toMillis())
                .count(stadiumTime.getCount())
                .build();
    }
}
