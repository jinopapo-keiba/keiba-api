package com.example.demo.contoller;

import com.example.demo.contoller.request.SaveHorseRequest;
import com.example.demo.contoller.response.GetHorseResponse;
import com.example.demo.entity.Horse;
import com.example.demo.repository.HorseRepository;
import com.example.demo.repository.dto.SaveHorseQueryParam;
import com.example.demo.valueobject.HorseGender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/horse")
public class HorseContorller {
    private final HorseRepository horseRepository;

    @GetMapping("/{id}")
    GetHorseResponse getHorse(@PathVariable("id") int id){
        Horse horse = horseRepository.fetchHorse(id);
        return GetHorseResponse.builder()
                .name(horse.getName())
                .gender(horse.getGender().getText())
                .build();
    }

    @PostMapping
    String saveHorse(@RequestBody SaveHorseRequest request){
        horseRepository.saveHorse(
                SaveHorseQueryParam.builder()
                        .name(request.getName())
                        .gender(HorseGender.toEnum(request.getGender()))
                        .build()
        );
        return "success";
    }


}
