package com.microservice.academia.domain.model.ports.spi;

import com.microservice.academia.domain.model.model.pensun.Pensum;

public interface CreatePensumPersistencePort {
    Pensum createPensum(Pensum pensum);
}
