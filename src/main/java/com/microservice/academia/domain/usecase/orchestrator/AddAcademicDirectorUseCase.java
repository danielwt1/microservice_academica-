package com.microservice.academia.domain.usecase.orchestrator;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.ports.api.AddAcademicDirectorServicePort;
import com.microservice.academia.domain.model.ports.spi.AddAcademicDirectorPersistencePort;
import com.microservice.academia.domain.usecase.GetUserModelUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class AddAcademicDirectorUseCase implements AddAcademicDirectorServicePort {
    private final AddAcademicDirectorPersistencePort addAcademicDirectorPersistencePort;
    private final GetUserModelUseCase getUserModelUseCase;

    @Override
    public void addAcademicDirector(Long academicProgramId, Long userId) {
        Long userModelId = getUserModelUseCase.action(userId).getId();
        if (userModelId == null) {
            throw new AcademiaExceptions("userId cannot be null", HttpStatus.NOT_FOUND);
        }
        addAcademicDirectorPersistencePort.addAcademicDirector(academicProgramId, userModelId);
    }
}
