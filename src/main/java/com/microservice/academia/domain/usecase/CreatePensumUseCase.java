package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.domain.model.ports.api.CreatePensumServicePort;
import com.microservice.academia.domain.model.ports.spi.CreatePensumPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePensumUseCase implements CreatePensumServicePort {
    private final CreatePensumPersistencePort createPensumPersistencePort;

    @Override
    public Pensum createPensum(Pensum pensum) {
        return createPensumPersistencePort.createPensum(pensum);
    }
}
