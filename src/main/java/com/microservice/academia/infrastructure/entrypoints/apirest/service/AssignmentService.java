package com.microservice.academia.infrastructure.entrypoints.apirest.service;

import com.microservice.academia.infrastructure.entrypoints.apirest.dto.request.AssignmentRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.response.AssignmentResponseDto;

public interface AssignmentService {
    void addAssignmentServicePort(AssignmentRequestDto assignmentDto);

    AssignmentResponseDto getAssignmentServicePort(Long assignmentId);
}
