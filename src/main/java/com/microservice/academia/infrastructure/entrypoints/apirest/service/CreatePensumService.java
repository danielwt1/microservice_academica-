package com.microservice.academia.infrastructure.entrypoints.apirest.service;

import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.PensumRequestDto;

public interface CreatePensumService {
    Pensum createPensum(PensumRequestDto pensumRequestDto);
}
