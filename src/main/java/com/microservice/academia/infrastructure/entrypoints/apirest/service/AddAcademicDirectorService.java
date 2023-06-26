package com.microservice.academia.infrastructure.entrypoints.apirest.service;

public interface AddAcademicDirectorService {
    void addAcademicDirector(Long academicProgramId, Long userId);
}
