package com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper;

import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.PensumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PensumEntityMapper {
    @Mapping(source = "programId.id", target = "programId.id")
    Pensum pensumEntityToPensum(PensumEntity pensumEntity);

    @Mapping(source = "programId.id", target = "programId.id")
    PensumEntity pensumToPensumEntity(Pensum pensum);
}
