package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.ports.spi.DeleteProgramPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgram.AcademicProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AcademicProgramJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DeleteProgramPersistencePortImpl implements DeleteProgramPersistencePort {
    private final AcademicProgramJpaRepository academicProgramJpaRepository;
    private final PensumJpaRepository pensumJpaRepository;

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
        List<PensumEntity> validateAssignmentPensum = pensumJpaRepository.findByProgramAcademicId(idProgram);
        if (!validateAssignmentPensum.isEmpty()) {
            throw new AcademiaExceptions("No es posible eliminar un programa con un pensum asignado", HttpStatus.BAD_REQUEST);
        }
        return programEntity;
    }
}
