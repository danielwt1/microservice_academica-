package com.microservice.academia.domain.model.ports.repositories;

import com.microservice.academia.domain.model.model.academy.Pensum;

public interface PensumPersistencePort {
    Pensum createPensum(Pensum pensum);

    void deletePensum(Long pensumId);
}
