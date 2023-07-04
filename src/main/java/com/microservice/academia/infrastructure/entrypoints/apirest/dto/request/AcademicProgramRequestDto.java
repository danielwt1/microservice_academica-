package com.microservice.academia.infrastructure.entrypoints.apirest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "DTO REQUEST para añadir un programa academico",
        name = "AcademicProgramRequestDto")
public class AcademicProgramRequestDto {
    @Schema(description = "nombre del programa academico", example = "programa de prueba")
    @NotEmpty(message = "El nombre del programa no puede estar vacío")
    @Size(min = 8, max = 40, message = "El nombre del programa debe tener una longitud de 8 a 40 caracteres")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo puede contener letras")
    private String name;

    @NotEmpty(message = "La descripción no puede estar vacía")
    @Size(min = 20, max = 200, message = "La descripcion debe tener una longitud maxima de 200 caracteres y minimo 20")
    @Schema(description = "descripcion del programa academico", example = "descripcion de prueba")
    private String description;

    @NotEmpty(message = "El ID del nivel educativo no puede estar vacío")
    @Schema(description = "id del nivel educativo", example = "1")
    private Long idLevelEducation;

    @Schema(description = "id del tipo de rol director academico", example = "1")
    private Long typeUserId;
}
