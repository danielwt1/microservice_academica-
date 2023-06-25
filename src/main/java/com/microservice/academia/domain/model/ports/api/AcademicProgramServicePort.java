package com.microservice.academia.domain.model.ports.api;

import com.microservice.academia.domain.model.model.AcademicProgram;

public interface AcademicProgramServicePort {
    AcademicProgram createAcademicProgram(AcademicProgram academicProgram);
}
