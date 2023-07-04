package com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper;

import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgramEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AcademicProgramEntityMapper {
    @Mapping(source = "educationLevel.id", target = "educationLevel.id")
    AcademicProgram programaEntityToPrograma(AcademicProgramEntity academicProgramEntity);

    // @Mapping(target = "typeUserId", source = "typeUserId")
    @Mapping(source = "educationLevel.id", target = "educationLevel.id")
    AcademicProgramEntity programaToProgramaEntity(AcademicProgram academicProgram);
}
