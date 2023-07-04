package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAcademicProgram {
    private final AcademyPersistencePort academyPersistencePort;

    public void action(Long idProgram) {
        academyPersistencePort.deleteAcademicProgram(idProgram);
    }
}
