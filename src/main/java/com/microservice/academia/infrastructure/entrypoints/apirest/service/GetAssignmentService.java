package com.microservice.academia.infrastructure.entrypoints.apirest.service;

import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentResponseDto;

public interface GetAssignmentService {
    AssignmentResponseDto getAssignmentServicePort(Long assignmentId);
}
