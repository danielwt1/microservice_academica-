package com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper;

import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.PensumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PensumEntityMapper {
    @Mapping(source = "programId.id", target = "programId.id")
    Pensum pensumEntityToPensum(PensumEntity pensumEntity);

    @Mapping(source = "programId.id", target = "programId.id")
    PensumEntity pensumToPensumEntity(Pensum pensum);

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
