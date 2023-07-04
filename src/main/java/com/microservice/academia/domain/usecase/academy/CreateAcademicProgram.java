package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAcademicProgram {
    private final AcademyPersistencePort academyPersistencePort;

    public void action(AcademicProgram academicProgram) {
        academyPersistencePort.createAcademicProgram(academicProgram);
    }
}
