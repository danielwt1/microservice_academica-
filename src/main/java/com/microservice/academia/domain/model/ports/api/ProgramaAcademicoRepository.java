package com.microservice.academia.domain.model.ports.api;

import com.microservice.academia.domain.model.model.ProgramaAcademico;

public interface ProgramaAcademicoRepository {
    ProgramaAcademico saveProgramaAcademico(ProgramaAcademico programaAcademico);

    ProgramaAcademico getProgramaById(Long id);
}
