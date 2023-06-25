package com.microservice.academia.infrastructure.entrypoints.apirest.service;

import com.microservice.academia.domain.model.model.AcademicProgram.AcademicProgram;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AcademicProgramRequestDto;

public interface CreateProgramService {
    AcademicProgram createAcademicProgram(AcademicProgramRequestDto academicProgramRequest);
}
