package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.model.academy.Assignment;
import com.microservice.academia.domain.model.ports.repositories.AssignmentPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AssignmentJpaRepository;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentResponseDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.mapper.AssignmentRequestMapper;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentPersistencePort assignmentPersistencePort;
    private final AssignmentJpaRepository assignmentJpaRepository; // deberia ser a travez de un puerto de persistencia
    private final AssignmentRequestMapper assignmentRequestMapper;

    @Override
    public void addAssignmentServicePort(AssignmentRequestDto assignmentDto) {
        Assignment assignmentRequest = assignmentRequestMapper.assignmentDtoToAssignment(assignmentDto);
        assignmentPersistencePort.addAssignment(assignmentRequest);
    }

    @Override
    public AssignmentResponseDto getAssignmentServicePort(Long assignmentId) {
        return assignmentJpaRepository.findAssignmentDetailsByMateriaId(assignmentId);
    }
}
