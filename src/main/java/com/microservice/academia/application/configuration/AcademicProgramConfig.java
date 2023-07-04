package com.microservice.academia.application.configuration;

import com.microservice.academia.domain.model.ports.api.userapi.UserAcademyService;
import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import com.microservice.academia.domain.model.ports.repositories.AssignmentPersistencePort;
import com.microservice.academia.domain.model.ports.repositories.PensumPersistencePort;
import com.microservice.academia.domain.usecase.userapi.GetUserModelUseCase;
import com.microservice.academia.domain.usecase.academy.AddAssignment;
import com.microservice.academia.domain.usecase.academy.CreateAcademicProgram;
import com.microservice.academia.domain.usecase.academy.CreatePensum;
import com.microservice.academia.domain.usecase.academy.DeleteAcademicProgram;
import com.microservice.academia.domain.usecase.academy.DeletePensum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcademicProgramConfig {
    @Bean
    public CreateAcademicProgram createAcademicProgram(AcademyPersistencePort academyPersistencePort) {
        return new CreateAcademicProgram(academyPersistencePort);
    }

    @Bean
    public DeleteAcademicProgram deleteAcademicProgram(AcademyPersistencePort academyPersistencePort) {
        return new DeleteAcademicProgram(academyPersistencePort);
    }

    @Bean
    public CreatePensum createPensum(PensumPersistencePort pensumPersistencePort) {
        return new CreatePensum(pensumPersistencePort);
    }

    @Bean
    public DeletePensum deletePensum(PensumPersistencePort pensumPersistencePort) {
        return new DeletePensum(pensumPersistencePort);
    }

    @Bean
    public AddAssignment addAssignment(AssignmentPersistencePort assignmentPersistencePort) {
        return new AddAssignment(assignmentPersistencePort);
    }

    // ------

    @Bean
    public GetUserModelUseCase getUserModelUseCase(UserAcademyService userAcademyService) {
        return new GetUserModelUseCase(userAcademyService);
    }
}
