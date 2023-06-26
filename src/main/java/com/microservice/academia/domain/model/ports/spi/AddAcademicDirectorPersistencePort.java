package com.microservice.academia.domain.model.ports.spi;

public interface AddAcademicDirectorPersistencePort {
    void addAcademicDirector(Long academicProgramId, Long userId);
}
