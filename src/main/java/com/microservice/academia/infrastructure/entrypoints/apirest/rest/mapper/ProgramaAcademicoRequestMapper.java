package com.microservice.academia.infrastructure.entrypoints.apirest.rest.mapper;

import com.microservice.academia.domain.model.model.ProgramaAcademico;
import com.microservice.academia.infrastructure.entrypoints.apirest.rest.dto.ProgramaAcademicoRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProgramaAcademicoRequestMapper {

    @Mapping(source = "idLevelEducation", target = "nivelAcademico.id")
    @Mapping(target = "id", ignore = true)
    ProgramaAcademico toProgramaAcademico(ProgramaAcademicoRequestDTO dto);
}
