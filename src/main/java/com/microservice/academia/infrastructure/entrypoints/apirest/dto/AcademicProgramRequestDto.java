package com.microservice.academia.infrastructure.entrypoints.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class AcademicProgramRequestDto {
    @NotEmpty
    @NotNull
    @Size(min = 8, max = 40, message = "El nombre del programa debe tener una longitud de 8 a 40 caracteres")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo puede contener letras")
    private String name;

    @NotEmpty
    @NotNull
    @Size(min = 20, max = 200, message = "La descripcion debe tener una longitud maxima de 200 caracteres y minimo 20")
    private String description;

    @NotEmpty
    @NotNull
    private Long idLevelEducation;
}
