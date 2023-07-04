package com.microservice.academia.infrastructure.entrypoints.apirest.mapper;

import com.microservice.academia.domain.model.model.academy.Assignment;
import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentResponseMapper {

    @Mapping(target = "programId", source = "pensum.programId.id")
    @Mapping(target = "pensumId", source = "pensum.id")
    @Mapping(target = "materiaId", source = "assignment.id")
    @Mapping(target = "nombre", source = "assignment.name")
    @Mapping(target = "description", source = "assignment.description")
    @Mapping(target = "prerequisitoId", source = "assignment.preAssignmentId.id")
    AssignmentResponseDto toResponse(Assignment assignment, Pensum pensum);
}
