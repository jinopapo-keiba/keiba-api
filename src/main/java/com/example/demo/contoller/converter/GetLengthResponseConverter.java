package com.example.demo.contoller.converter;

import com.example.demo.contoller.response.GetLengthResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetLengthResponseConverter {
    public GetLengthResponse convert(List<Integer> length){
        return GetLengthResponse.builder()
                .length(length)
                .build();
    }
}
