package com.microservice.academia.infrastructure.entrypoints.apirest.service;

import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.PensumRequestDto;

public interface PensumService {
    Pensum createPensum(PensumRequestDto pensumDto);

    void deletePensum(Long pensumId);
}
