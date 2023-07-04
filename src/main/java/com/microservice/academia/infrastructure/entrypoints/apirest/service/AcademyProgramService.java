package com.microservice.academia.infrastructure.entrypoints.apirest.service;

import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AcademicProgramRequestDto;

public interface AcademyProgramService {
    void assignAcademicDirector(Long academicProgramId, Long userId);

    AcademicProgram createAcademicProgram(AcademicProgramRequestDto academicProgramRequest);

    void deleteAcademicProgram(Long idProgram);
}
