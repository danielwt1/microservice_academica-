package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.ports.api.DeletePensumServicePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.DeletePensumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePensumServiceImpl implements DeletePensumService {
    private final DeletePensumServicePort deletePensumServicePort;

    @Override
    public void deletePensum(Long idPensum) {
        deletePensumServicePort.deletePensum(idPensum);
    }
}
