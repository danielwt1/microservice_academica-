package com.microservice.academia.domain.model.ports.spi;

import com.microservice.academia.domain.model.model.AcademicProgram;

public interface CreateProgramPersistencePort {
    AcademicProgram createAcademicProgram(AcademicProgram academicProgram);
}
