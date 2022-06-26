package com.example.demo.utils;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.util.Date;

@UtilityClass
public class DateUtils {
    public Date convertLocalDateTime2Date(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}
