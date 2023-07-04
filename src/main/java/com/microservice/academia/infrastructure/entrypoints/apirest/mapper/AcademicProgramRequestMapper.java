package com.microservice.academia.infrastructure.entrypoints.apirest.mapper;

import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.request.AcademicProgramRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AcademicProgramRequestMapper {
    @Mapping(source = "idLevelEducation", target = "educationLevel.id")
    @Mapping(target = "id", ignore = true)
    AcademicProgram toAcademicProgram(AcademicProgramRequestDto requestDto);
}
