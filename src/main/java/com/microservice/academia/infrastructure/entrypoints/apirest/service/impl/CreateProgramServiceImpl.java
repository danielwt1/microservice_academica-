package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.model.AcademicProgram.AcademicProgram;
import com.microservice.academia.domain.model.ports.api.CreateProgramServicePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AcademicProgramRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.mapper.AcademicProgramRequestMapper;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.CreateProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProgramServiceImpl implements CreateProgramService {
    private final CreateProgramServicePort createProgramServicePort;
    private final AcademicProgramRequestMapper requestMapper;

    @Override
    public AcademicProgram createAcademicProgram(AcademicProgramRequestDto academicProgramRequest) {
        return createProgramServicePort.createAcademicProgram(requestMapper.toAcademicProgram(academicProgramRequest));
    }
}
