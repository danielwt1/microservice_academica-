package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import com.microservice.academia.domain.usecase.userapi.GetUserModelUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class AddAcademicDirector {
    private final AcademyPersistencePort academyPersistencePort;
    private final GetUserModelUseCase getUserModelUseCase;

    public void action(Long academicProgramId, Long userId) {
        Long userModelId = getUserModelUseCase.action(userId).getId();
        if (userModelId == null) {
            throw new AcademiaExceptions("Usuario no registrado", HttpStatus.NOT_FOUND);
        }
        academyPersistencePort.assignAcademicDirector(academicProgramId, userModelId);
    }
}
