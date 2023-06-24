package com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper;

import com.microservice.academia.domain.model.model.ProgramaAcademico;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.ProgramaAcademicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramaAcademicoMapper {
    @Mapping(source = "id", target = "nivelAcademico.id")
    ProgramaAcademico programaEntityToPrograma(ProgramaAcademicoEntity programaAcademicoEntity);

    @Mapping(source = "nivelAcademico.id", target = "idLevelEducation")
    ProgramaAcademicoEntity programaToProgramaEntity(ProgramaAcademico programaAcademico);
}
