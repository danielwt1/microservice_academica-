package com.microservice.academia.domain.model.ports.spi;

public interface DeleteProgramPersistencePort {
    void deleteAcademicProgram(Long idProgram);
}
