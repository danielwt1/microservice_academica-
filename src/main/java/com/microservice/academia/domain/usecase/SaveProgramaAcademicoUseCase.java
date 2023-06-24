package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.model.ProgramaAcademico;
import com.microservice.academia.domain.model.ports.api.ProgramaAcademicoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveProgramaAcademicoUseCase {
    private final ProgramaAcademicoRepository programaAcademicoRepository;

    public ProgramaAcademico action(ProgramaAcademico programaAcademico) {
        return programaAcademicoRepository.saveProgramaAcademico(programaAcademico);
    }
}
