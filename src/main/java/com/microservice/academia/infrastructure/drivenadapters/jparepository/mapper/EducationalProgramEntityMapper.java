package com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper;

import com.microservice.academia.domain.model.model.AcademicProgram;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.EducationalProgramEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EducationalProgramEntityMapper {

    @Mapping(source = "educationLevel.id", target = "educationLevel.id")
    AcademicProgram programaEntityToPrograma(EducationalProgramEntity educationalProgramEntity);

    @Mapping(source = "educationLevel.id", target = "educationLevel.id")
    EducationalProgramEntity programaToProgramaEntity(AcademicProgram academicProgram);
}
