package com.microservice.academia.infrastructure.entrypoints.apirest.service;

import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentResponseDto;

public interface AssignmentService {
    void addAssignmentServicePort(AssignmentRequestDto assignmentDto);

    AssignmentResponseDto getAssignmentServicePort(Long assignmentId);
}
