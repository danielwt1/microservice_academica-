package com.microservice.academia.infrastructure.entrypoints.apirest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "DTO REQUEST para añadir una materia a un pensum",
        name = "AssignmentRequestDto")
public class AssignmentRequestDto {
    @Schema(description = "Id del pensum", example = "1")
    private Long pensumId;

    @Size(min = 2, max = 30, message = "El nombre de la materia debe tener una longitud de 2 a 30 caracteres")
    @Schema(description = "nombre de la materia", example = "materia de prueba")
    @Valid
    private String name;

    @Size(min = 5, max = 200, message = "La descripcion debe tener una longitud de 5 a 500 caracteres")
    @Schema(description = "descripcion de la materia", example = "descripcion de prueba")
    private String description;

    @Schema(description = "id de la materia de prerrequisito", example = "descripcion de prueba")
    private Long preAssignmentId;
}
