package com.microservice.academia.domain.model.ports.repositories;

import com.microservice.academia.domain.model.model.academy.AcademicProgram;

public interface AcademyPersistencePort {
    void assignAcademicDirector(Long academicProgramId, Long userId);

    AcademicProgram createAcademicProgram(AcademicProgram academicProgram);

    void deleteAcademicProgram(Long idProgram);
}
