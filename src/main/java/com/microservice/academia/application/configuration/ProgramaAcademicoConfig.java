package com.microservice.academia.application.configuration;

import com.microservice.academia.domain.model.ports.api.ProgramaAcademicoRepository;
import com.microservice.academia.domain.usecase.SaveProgramaAcademicoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramaAcademicoConfig {
    @Bean
    public SaveProgramaAcademicoUseCase saveProgramaAcademicoUseCase(ProgramaAcademicoRepository programaAcademicoRepository) {
        return new SaveProgramaAcademicoUseCase(programaAcademicoRepository);
    }
}
