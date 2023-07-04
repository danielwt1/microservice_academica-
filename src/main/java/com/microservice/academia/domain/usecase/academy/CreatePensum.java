package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.domain.model.ports.repositories.PensumPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePensum {
    private final PensumPersistencePort pensumPersistencePort;

    public Pensum action(Pensum pensum) {
        return pensumPersistencePort.createPensum(pensum);
    }
}
