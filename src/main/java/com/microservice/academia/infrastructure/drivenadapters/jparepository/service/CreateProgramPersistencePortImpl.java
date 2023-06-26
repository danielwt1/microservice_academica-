package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.model.model.AcademicProgram.AcademicProgram;
import com.microservice.academia.domain.model.ports.spi.CreateProgramPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.EducationalProgramEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.EducationalProgramJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CreateProgramPersistencePortImpl implements CreateProgramPersistencePort {
    private final EducationalProgramJpaRepository educationalProgramJpaRepository;
    private final EducationalProgramEntityMapper educationalProgramEntityMapper;

    @Override
    public AcademicProgram createAcademicProgram(AcademicProgram academicProgram) {
        if (academicProgram == null) {
            throw new IllegalArgumentException("El objeto programa academico no puede ser nulo");
        }
        AcademicProgramEntity academicProgramEntity = educationalProgramEntityMapper.programaToProgramaEntity(academicProgram);
        return educationalProgramEntityMapper.programaEntityToPrograma(educationalProgramJpaRepository.save(academicProgramEntity));
    }
}
