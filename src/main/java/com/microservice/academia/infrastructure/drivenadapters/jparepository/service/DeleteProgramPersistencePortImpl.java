package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.ports.spi.DeleteProgramPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.EducationalProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.EducationalProgramJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DeleteProgramPersistencePortImpl implements DeleteProgramPersistencePort {
    private final EducationalProgramJpaRepository educationalProgramJpaRepository;

    @Override
    public void DeleteAcademicProgram(Long idProgram) {
        Optional<EducationalProgramEntity> optionalProgramEntity = this.educationalProgramJpaRepository.findById(idProgram);
        if (optionalProgramEntity.isEmpty()){
            throw new AcademiaExceptions("Program not found", HttpStatus.NOT_FOUND);
        }
        this.educationalProgramJpaRepository.deleteById(idProgram);
    }
}
