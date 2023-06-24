package com.microservice.academia.mock.programaAcademico;

import com.microservice.academia.domain.model.model.ProgramaAcademico;

public class ProgramaAcademicoMocks {
    public static ProgramaAcademico getPrograma(Long id) {
        return ProgramaAcademico.builder()
                .id(id)
                .name("Programa academico de prueba")
                .description("Programa academico de prueba")
                .nivelAcademico(NivelAcademicoMocks.getNivelAcademico(id))
                .build();
    }
}
