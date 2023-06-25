package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.ports.api.DeleteProgramServicePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.DeleteProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProgramServiceImpl implements DeleteProgramService {
    private final DeleteProgramServicePort deleteProgramServicePort;

    @Override
    public void DeleteAcademicProgram(Long idProgram) {
        deleteProgramServicePort.DeleteAcademicProgram(idProgram);
    }
}
