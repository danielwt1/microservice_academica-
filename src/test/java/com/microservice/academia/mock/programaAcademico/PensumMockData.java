package com.microservice.academia.mock.programaAcademico;

import com.microservice.academia.domain.model.model.academy.Pensum;

public class PensumMockData {
    public static Pensum getPensum(Long id) {
        return Pensum.builder()
                .id(id)
                .programId(AcademicProgramMockData.getProgram(id))
                .year(2023)
                .build();
    }
}
