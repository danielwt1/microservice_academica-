package com.microservice.academia.application.configuration;

import com.microservice.academia.domain.model.ports.spi.AcademicProgramPersistencePort;
import com.microservice.academia.domain.usecase.CreateAcademicProgramUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcademicProgramConfig {
    @Bean
    public CreateAcademicProgramUseCase saveProgramaAcademicoUseCase(AcademicProgramPersistencePort academicProgramPersistencePort) {
        return new CreateAcademicProgramUseCase(academicProgramPersistencePort);
    }
}
