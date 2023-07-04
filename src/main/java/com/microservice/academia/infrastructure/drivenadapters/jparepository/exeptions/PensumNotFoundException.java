package com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class PensumNotFoundException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public PensumNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
