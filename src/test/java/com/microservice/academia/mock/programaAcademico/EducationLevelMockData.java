package com.microservice.academia.mock.programaAcademico;

import com.microservice.academia.domain.model.model.academy.EducationLevel;

public class EducationLevelMockData {
    public static EducationLevel getEducationLevel(Long id) {
        return EducationLevel.builder()
                .id(id)
                .name("Nivel 1")
                .description("Nivel 1")
                .build();
    }
}
