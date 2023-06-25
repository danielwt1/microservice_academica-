package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.model.AcademicProgram.AcademicProgram;
import com.microservice.academia.domain.model.ports.api.CreateProgramServicePort;
import com.microservice.academia.domain.model.ports.spi.CreateProgramPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAcademicProgramUseCase implements CreateProgramServicePort {
    private final CreateProgramPersistencePort createProgramPersistencePort;

    @Override
    public AcademicProgram createAcademicProgram(AcademicProgram academicProgram) {
        return createProgramPersistencePort.createAcademicProgram(academicProgram);
    }
}
