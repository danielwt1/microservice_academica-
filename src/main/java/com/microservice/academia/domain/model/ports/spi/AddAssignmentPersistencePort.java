package com.microservice.academia.domain.model.ports.spi;

import com.microservice.academia.domain.model.model.pensun.Assignment;

public interface AddAssignmentPersistencePort {
    void addAssignmentServicePort(Assignment assignment);
}
