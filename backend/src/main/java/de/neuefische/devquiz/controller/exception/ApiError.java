package de.neuefische.devquiz.controller.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private String errorMessage;
    private String exceptionMessage;
    private LocalDateTime timestamp;

    public ApiError(String errorMessage, String exceptionMessage) {
        this.errorMessage = errorMessage;
        this.exceptionMessage = exceptionMessage;
        this.timestamp = LocalDateTime.now();
    }
}
