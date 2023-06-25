package com.microservice.academia.domain.model.ports.spi;

import com.microservice.academia.domain.model.model.AcademicProgram;

public interface AcademicProgramPersistencePort {
    AcademicProgram createAcademicProgram(AcademicProgram academicProgram);
}
