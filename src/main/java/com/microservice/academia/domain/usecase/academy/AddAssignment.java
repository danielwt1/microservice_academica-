package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.model.model.academy.Assignment;
import com.microservice.academia.domain.model.ports.repositories.AssignmentPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddAssignment {
    private final AssignmentPersistencePort assignmentPersistencePort;

    public void action(Assignment assignment) {
        assignmentPersistencePort.addAssignment(assignment);
    }
}
