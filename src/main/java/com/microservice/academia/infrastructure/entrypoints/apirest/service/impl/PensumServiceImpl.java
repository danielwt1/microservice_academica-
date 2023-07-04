package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.domain.model.ports.repositories.PensumPersistencePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.PensumRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.mapper.PensumRequestMapper;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.PensumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PensumServiceImpl implements PensumService {
    private final PensumPersistencePort pensumPersistencePort;
    private final PensumRequestMapper pensumRequestMapper;

    @Override
    public Pensum createPensum(PensumRequestDto pensumDto) {
        return pensumPersistencePort.createPensum(pensumRequestMapper.pensumDtoToPensum(pensumDto));
    }

    @Override
    public void deletePensum(Long pensumId) {
        pensumPersistencePort.deletePensum(pensumId);
    }
}
