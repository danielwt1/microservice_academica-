package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.model.ports.repositories.PensumPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletePensum {
    private final PensumPersistencePort pensumPersistencePort;

    public void action(Long idPensum) {
        pensumPersistencePort.deletePensum(idPensum);
    }
}
