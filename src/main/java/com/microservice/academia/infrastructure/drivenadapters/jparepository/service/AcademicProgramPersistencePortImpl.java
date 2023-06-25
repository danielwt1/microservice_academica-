package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.model.model.AcademicProgram;
import com.microservice.academia.domain.model.ports.spi.AcademicProgramPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.EducationalProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.EducationalProgramEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.EducationalProgramJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AcademicProgramPersistencePortImpl implements AcademicProgramPersistencePort {
    private final EducationalProgramJpaRepository educationalProgramJpaRepository;
    private final EducationalProgramEntityMapper educationalProgramEntityMapper;

    @Override
    public AcademicProgram createAcademicProgram(AcademicProgram academicProgram) {
        if (academicProgram == null) {
            throw new IllegalArgumentException("El objeto programa academico no puede ser nulo");
        }
        EducationalProgramEntity educationalProgramEntity = educationalProgramEntityMapper.programaToProgramaEntity(academicProgram);
        return educationalProgramEntityMapper.programaEntityToPrograma(educationalProgramJpaRepository.save(educationalProgramEntity));
    }
}
