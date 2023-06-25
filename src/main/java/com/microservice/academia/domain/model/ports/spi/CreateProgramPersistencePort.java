package com.microservice.academia.domain.model.ports.spi;

import com.microservice.academia.domain.model.model.AcademicProgram.AcademicProgram;

public interface CreateProgramPersistencePort {
    AcademicProgram createAcademicProgram(AcademicProgram academicProgram);
}
