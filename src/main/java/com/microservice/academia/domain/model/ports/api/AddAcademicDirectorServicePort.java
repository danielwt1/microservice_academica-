package com.microservice.academia.domain.model.ports.api;

public interface AddAcademicDirectorServicePort {
    void addAcademicDirector(Long academicProgramId, Long userId);
}
