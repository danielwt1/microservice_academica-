package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.ports.api.DeletePensumServicePort;
import com.microservice.academia.domain.model.ports.spi.DeletePensumPersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePensumUseCase implements DeletePensumServicePort {
    private final DeletePensumPersistencePort deletePensumPersistencePort;

    @Override
    public void deletePensum(Long idPensum) {
        deletePensumPersistencePort.deletePensum(idPensum);
    }
}
