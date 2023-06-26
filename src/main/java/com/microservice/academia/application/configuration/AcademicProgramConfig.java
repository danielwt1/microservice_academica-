package com.microservice.academia.application.configuration;

import com.microservice.academia.domain.model.ports.api.services.UserAcademyService;
import com.microservice.academia.domain.model.ports.spi.AddAcademicDirectorPersistencePort;
import com.microservice.academia.domain.model.ports.spi.CreateProgramPersistencePort;
import com.microservice.academia.domain.model.ports.spi.DeleteProgramPersistencePort;
import com.microservice.academia.domain.usecase.CreateAcademicProgramUseCase;
import com.microservice.academia.domain.usecase.DeleteAcademicProgramUseCase;
import com.microservice.academia.domain.usecase.GetUserModelUseCase;
import com.microservice.academia.domain.usecase.orchestrator.AddAcademicDirectorUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcademicProgramConfig {
    @Bean
    public CreateAcademicProgramUseCase saveProgramaAcademicoUseCase(CreateProgramPersistencePort createProgramPersistencePort) {
        return new CreateAcademicProgramUseCase(createProgramPersistencePort);
    }

    @Bean
    public DeleteAcademicProgramUseCase deleteAcademicProgramUseCase(DeleteProgramPersistencePort deleteProgramPersistencePort) {
        return new DeleteAcademicProgramUseCase(deleteProgramPersistencePort);
    }

    @Bean
    public AddAcademicDirectorUseCase addAcademicDirectorUseCase(AddAcademicDirectorPersistencePort addAcademicDirectorPersistencePort,
                                                                 GetUserModelUseCase getUserModelUseCase) {
        return new AddAcademicDirectorUseCase(addAcademicDirectorPersistencePort, getUserModelUseCase);
    }

    @Bean
    public GetUserModelUseCase getUserModelUseCase(UserAcademyService userAcademyService) {
        return new GetUserModelUseCase(userAcademyService);
    }
}
