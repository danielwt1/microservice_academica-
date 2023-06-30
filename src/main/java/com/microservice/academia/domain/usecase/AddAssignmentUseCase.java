package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.model.pensun.Assignment;
import com.microservice.academia.domain.model.ports.api.AddAssignmentServicePort;
import com.microservice.academia.domain.model.ports.spi.AddAssignmentPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddAssignmentUseCase implements AddAssignmentServicePort {
    private final AddAssignmentPersistencePort addAssignmentPersistencePort;

    @Override
    public void addAssignmentServicePort(Assignment assignment) {
        addAssignmentPersistencePort.addAssignmentServicePort(assignment);
    }
}
