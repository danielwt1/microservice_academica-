package com.microservice.academia.domain.model.ports.spi;

public interface DeletePensumPersistencePort {
    void deletePensum(Long idPensum);
}
