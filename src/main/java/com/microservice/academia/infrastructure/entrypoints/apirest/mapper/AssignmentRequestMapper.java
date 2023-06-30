package com.microservice.academia.infrastructure.entrypoints.apirest.mapper;

import com.microservice.academia.domain.model.model.pensun.Assignment;
import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AssignmentRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "pensumId", target = "pensumId.id")
    @Mapping(source = "preAssignmentId", target = "preAssignmentId.id")
    Assignment assignmentDtoToAssignment(AssignmentRequestDto requestDto);

    default Pensum map(Long value) {
        if (value == null) {
            return null;
        }
        return Pensum.builder().id(value).build();
    }

    default Long map(Pensum value) {
        if (value == null) {
            return null;
        }
        return value.getId();
    }

    default Long map(Assignment value) {
        if (value == null) {
            return null;
        }
        return value.getId();
    }
}
