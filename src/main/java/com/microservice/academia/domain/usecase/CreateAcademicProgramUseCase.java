package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.model.AcademicProgram;
import com.microservice.academia.domain.model.ports.api.AcademicProgramServicePort;
import com.microservice.academia.domain.model.ports.spi.AcademicProgramPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAcademicProgramUseCase implements AcademicProgramServicePort {
    private final AcademicProgramPersistencePort academicProgramPersistencePort;

    @Override
    public AcademicProgram createAcademicProgram(AcademicProgram academicProgram) {
        return academicProgramPersistencePort.createAcademicProgram(academicProgram);
    }
}
