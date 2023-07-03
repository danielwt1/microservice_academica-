package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AssignmentJpaRepository;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentResponseDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.GetAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAssignmentServiceImpl implements GetAssignmentService {
    private final AssignmentJpaRepository assignmentJpaRepository;

    @Override
    public AssignmentResponseDto getAssignmentServicePort(Long assignmentId) {
       return assignmentJpaRepository.findAssignmentDetailsByMateriaId(assignmentId);
    }
}
