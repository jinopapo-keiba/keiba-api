package com.example.demo.repository.dto;

import java.time.Duration;

public record DeviBase (
        Duration meanFullTime,
        Duration meanLastRapTime,
        Float stdDeviFullTime,
        Float stdDeviLastRapTime
){ }
