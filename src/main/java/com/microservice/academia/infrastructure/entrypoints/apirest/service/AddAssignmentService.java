package com.microservice.academia.infrastructure.entrypoints.apirest.service;

import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentRequestDto;

public interface AddAssignmentService {
    void addAssignment(AssignmentRequestDto assignmentRequestDto);
}
