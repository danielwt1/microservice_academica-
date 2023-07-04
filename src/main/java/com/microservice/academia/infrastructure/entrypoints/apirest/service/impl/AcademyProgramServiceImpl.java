package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.request.AcademicProgramRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.mapper.AcademicProgramRequestMapper;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.AcademyProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class AcademyProgramServiceImpl implements AcademyProgramService {
    private final AcademyPersistencePort academyPersistencePort;
    private final AcademicProgramRequestMapper requestMapper;

    @Override
    public void assignAcademicDirector(Long academicProgramId, Long userId) {
        academyPersistencePort.assignAcademicDirector(academicProgramId, userId);
    }

    @Override
    public AcademicProgram createAcademicProgram(@Valid AcademicProgramRequestDto academicProgramRequest) {
        return academyPersistencePort.createAcademicProgram(requestMapper.toAcademicProgram(academicProgramRequest));
    }

    @Override
    public void deleteAcademicProgram(Long idProgram) {
        academyPersistencePort.deleteAcademicProgram(idProgram);
    }
}
