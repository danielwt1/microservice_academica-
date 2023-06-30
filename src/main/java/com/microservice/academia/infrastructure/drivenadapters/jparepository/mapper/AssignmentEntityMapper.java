package com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper;

import com.microservice.academia.domain.model.model.pensun.Assignment;
import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.AssignmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentEntityMapper {

    @Mapping(source = "pensumId.id", target = "pensumId")
    AssignmentEntity assignmentToEntity(Assignment assignment);

    @Mapping(source = "pensumId", target = "pensumId.id")
    Assignment entityToAssignment(AssignmentEntity entity);

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

}
