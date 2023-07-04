package com.microservice.academia.infrastructure.entrypoints.apirest.mapper;

import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.request.PensumRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PensumRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "programId", target = "programId.id")
    Pensum pensumDtoToPensum(PensumRequestDto requestDto);


}
