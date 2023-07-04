package com.microservice.academia.infrastructure.entrypoints.apirest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "DTO Response para la consulta detallada de una materia",
        name = "AssignmentResponseDto")
public class AssignmentResponseDto {
    @Schema(description = "Id del programa academico", example = "1")
    private Long programId;

    @Schema(description = "Id del pensum", example = "1")
    private Long pensumId;

    @Schema(description = "Id de la materia", example = "1")
    private Long materiaId;

    @Schema(description = "nombre de la materia", example = "1")
    private String nombre;

    @Schema(description = "descripcion de la materia", example = "1")
    private String description;

    @Schema(description = "Id de la materia prerrequisito", example = "1")
    private Long prerequisitoId;
}
