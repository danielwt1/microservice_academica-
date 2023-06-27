package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.ports.spi.DeleteProgramPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgram.AcademicProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AcademicProgramJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DeleteProgramPersistencePortImpl implements DeleteProgramPersistencePort {
    private final AcademicProgramJpaRepository academicProgramJpaRepository;

    @Override
    public void deleteAcademicProgram(Long idProgram) {
        AcademicProgramEntity programEntity = validateProgramDeletion(idProgram);
        academicProgramJpaRepository.delete(programEntity);
    }

    private AcademicProgramEntity validateProgramDeletion(Long idProgram) {
        AcademicProgramEntity programEntity = academicProgramJpaRepository.findById(idProgram)
                .orElseThrow(() -> new AcademiaExceptions("Program not found with ID: " + idProgram, HttpStatus.NOT_FOUND));
        if (programEntity.getTypeUserId() != null) {
            throw new AcademiaExceptions("Cannot delete an academic program with an assigned director", HttpStatus.BAD_REQUEST);
        }
        return programEntity;
    }
}
