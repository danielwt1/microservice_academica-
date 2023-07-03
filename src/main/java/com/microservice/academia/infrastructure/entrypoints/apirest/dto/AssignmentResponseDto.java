package com.microservice.academia.infrastructure.entrypoints.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class AssignmentResponseDto {
    private Long programId;
    private Long pensumId;
    private Long materiaId;
    private String nombre;
    private String description;
    private Long prerequisitoId;
}
