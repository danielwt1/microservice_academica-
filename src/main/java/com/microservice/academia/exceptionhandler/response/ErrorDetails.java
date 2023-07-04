package com.microservice.academia.exceptionhandler.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Schema(description = "DTO genérico para respuesta de errores en la aplicación", name = "ErrorDetails")
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    private LocalDate date;
    private String message;
    private String path;
}
