package com.example.demo.contoller.intercepter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionInterceptor {

    private final ObjectMapper objectMapper;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String catchException(Exception e, HttpServletRequest request) throws JsonProcessingException {
      log.error("error",e);
      log.error(objectMapper.writeValueAsString(request.getParameterMap()));
      return "error";
    }
}
