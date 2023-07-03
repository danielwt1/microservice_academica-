package com.microservice.academia.infrastructure.entrypoints.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class AssignmentRequestDto {
    private Long pensumId;

    @Size(min = 2, max = 30, message = "El nombre de la materia debe tener una longitud de 2 a 30 caracteres")
    private String name;

    @Size(min = 5, max = 200, message = "La descripcion debe tener una longitud de 5 a 500 caracteres")
    private String description;

    private Long preAssignmentId;
}
