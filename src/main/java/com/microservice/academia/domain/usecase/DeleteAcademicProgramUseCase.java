package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.ports.api.DeleteProgramServicePort;
import com.microservice.academia.domain.model.ports.spi.DeleteProgramPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAcademicProgramUseCase implements DeleteProgramServicePort {
    private final DeleteProgramPersistencePort deleteProgramPersistencePort;

    @Override
    public void DeleteAcademicProgram(Long idProgram) {
        deleteProgramPersistencePort.DeleteAcademicProgram(idProgram);
    }
}

