package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.model.pensun.Assignment;
import com.microservice.academia.domain.model.ports.api.AddAssignmentServicePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.mapper.AssignmentRequestMapper;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.AddAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAssignmentServiceImpl implements AddAssignmentService {
    private final AddAssignmentServicePort addAssignmentServicePort;
    private final AssignmentRequestMapper requestMapper;

    @Override
    public void addAssignment(AssignmentRequestDto assignmentRequestDto) {
        Assignment assignmentRequest = requestMapper.assignmentDtoToAssignment(assignmentRequestDto);
        addAssignmentServicePort.addAssignmentServicePort(assignmentRequest);
    }
}
