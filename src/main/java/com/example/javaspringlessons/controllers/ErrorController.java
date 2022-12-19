package com.example.javaspringlessons.controllers;

import com.example.javaspringlessons.models.dto.ErrorDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(RuntimeException.class)
    public ErrorDTO errorValidation(RuntimeException e) {
        return new ErrorDTO(500, e.getMessage());
    }
}
