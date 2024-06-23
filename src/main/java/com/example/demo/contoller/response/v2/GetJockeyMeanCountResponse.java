package com.example.demo.contoller.response.v2;

import com.example.demo.valueobject.Range;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetJockeyMeanCountResponse {
    String stadium;
    Range range;
    float count;
}
