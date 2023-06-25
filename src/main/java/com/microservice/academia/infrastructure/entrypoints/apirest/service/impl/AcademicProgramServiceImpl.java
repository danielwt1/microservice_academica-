package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.model.AcademicProgram;
import com.microservice.academia.domain.model.ports.api.AcademicProgramServicePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AcademicProgramRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.mapper.AcademicProgramRequestMapper;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.AcademicProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcademicProgramServiceImpl implements AcademicProgramService {
    private final AcademicProgramServicePort academicProgramServicePort;
    private final AcademicProgramRequestMapper requestMapper;

    @Override
    public AcademicProgram createAcademicProgram(AcademicProgramRequestDto academicProgramRequest) {
        return academicProgramServicePort.createAcademicProgram(requestMapper.toAcademicProgram(academicProgramRequest));
    }
}
