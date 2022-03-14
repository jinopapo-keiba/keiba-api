package com.example.demo.contoller;

import com.example.demo.service.StadiumService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/stadium")
public class StadiumController {
    private StadiumService stadiumService;

    @GetMapping("/ran")
    public List<String> getRanStadium(String raceId,Integer raceLength) {
        return stadiumService.fetchRanStadium(raceId, raceLength);
    }
}
