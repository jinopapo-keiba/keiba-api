package com.example.demo.contoller;

import com.example.demo.contoller.request.SaveRaceRequest;
import com.example.demo.converter.RaceConverter;
import com.example.demo.entity.Race;
import com.example.demo.service.RaceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

@RequestMapping("/v1/race")
@AllArgsConstructor
@RestController
@Slf4j
public class RaceController {
    private RaceService raceService;
    private RaceConverter raceConverter;
    private DateFormat dateFormat;

    @PostMapping
    String saveRace(@RequestBody SaveRaceRequest saveRaceRequest) {
        raceService.saveRace(raceConverter.converter(saveRaceRequest));
        return "success";
    }

    @GetMapping
    List<Race> getRace(String stadium, Integer round, String raceDate) throws ParseException {
        return raceService.fetchRace(
                stadium,
                round,
                dateFormat.parse(raceDate)
        );
    }
}
