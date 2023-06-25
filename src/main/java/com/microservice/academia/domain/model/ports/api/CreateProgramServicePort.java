package com.microservice.academia.domain.model.ports.api;

import com.microservice.academia.domain.model.model.AcademicProgram.AcademicProgram;

public interface CreateProgramServicePort {
    AcademicProgram createAcademicProgram(AcademicProgram academicProgram);
}
