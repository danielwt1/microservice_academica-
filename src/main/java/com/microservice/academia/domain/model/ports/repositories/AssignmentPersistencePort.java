package com.microservice.academia.domain.model.ports.repositories;

import com.microservice.academia.domain.model.model.academy.Assignment;

public interface AssignmentPersistencePort {
    void addAssignment(Assignment assignment);
}
