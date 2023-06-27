package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.domain.model.ports.api.CreatePensumServicePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.PensumRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.mapper.PensumRequestMapper;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.CreatePensumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePensumServiceImpl implements CreatePensumService {
    private final CreatePensumServicePort createPensumServicePort;
    private final PensumRequestMapper requestMapper;

    @Override
    public Pensum createPensum(PensumRequestDto pensumRequestDto) {
        return createPensumServicePort.createPensum(requestMapper.pensumDtoToPensum(pensumRequestDto));
    }
}
