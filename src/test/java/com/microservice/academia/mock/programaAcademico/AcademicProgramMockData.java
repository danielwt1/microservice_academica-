package com.microservice.academia.mock.programaAcademico;

import com.microservice.academia.domain.model.model.AcademicProgram;

public class AcademicProgramMockData {
    public static AcademicProgram getProgram(Long id) {
        return AcademicProgram.builder()
                .id(id)
                .name("Programa academico de prueba")
                .description("Programa academico de prueba")
                .educationLevel(EducationLevelMockData.getEducationLevel(id))
                .build();
    }
}
