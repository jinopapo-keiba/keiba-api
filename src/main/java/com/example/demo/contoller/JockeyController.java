package com.example.demo.contoller;

import com.example.demo.entity.Jockey;
import com.example.demo.repository.JockeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/jockey")
@AllArgsConstructor
public class JockeyController {
    private JockeyRepository jockeyRepository;

    @GetMapping
    Jockey getJockey(int id){
        return jockeyRepository.fetchJoceky(id);
    }

    @PostMapping
    String saveJockey(@RequestBody String name){
        jockeyRepository.saveJockey(name);
        return "success";
    }
}
