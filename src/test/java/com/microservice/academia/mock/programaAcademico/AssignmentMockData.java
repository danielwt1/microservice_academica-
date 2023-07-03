package com.microservice.academia.mock.programaAcademico;

import com.microservice.academia.domain.model.model.pensun.Assignment;

public class AssignmentMockData {
    public static Assignment getAssignment(Long id) {
        return Assignment.builder()
                .id(id)
                .name("name")
                .description("description")
                .build();
    }

}
