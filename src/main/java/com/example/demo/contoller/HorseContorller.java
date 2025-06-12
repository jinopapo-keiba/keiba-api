package com.example.demo.contoller;

import com.example.demo.contoller.response.GetHorseResponse;
import com.example.demo.entity.Horse;
import com.example.demo.repository.HorseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/horse")
@Tag(name = "Horse", description = "Operations about horse")
public class HorseContorller {
    private final HorseRepository horseRepository;

    @GetMapping("/{id}")
    @Operation(summary = "Get horse", description = "Fetch horse details by id")
    GetHorseResponse getHorse(@PathVariable("id") int id){
        Horse horse = horseRepository.fetchHorse(id);
        return GetHorseResponse.builder()
                .name(horse.getName())
                .gender(horse.getGender().getText())
                .build();
    }
}
