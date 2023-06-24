package com.microservice.academia.mock.programaAcademico;

import com.microservice.academia.domain.model.model.NivelAcademico;

public class NivelAcademicoMocks {
    public static NivelAcademico getNivelAcademico(Long id) {
        return NivelAcademico.builder()
                .id(id)
                .name("Nivel 1")
                .description("Nivel 1")
                .build();
    }
}
