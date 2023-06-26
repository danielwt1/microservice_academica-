package com.microservice.academia.infrastructure.entrypoints.apirest.service.impl;

import com.microservice.academia.domain.model.ports.api.AddAcademicDirectorServicePort;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.AddAcademicDirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAcademicDirectorServiceImpl implements AddAcademicDirectorService {
    private final AddAcademicDirectorServicePort academicDirectorServicePort;

    @Override
    public void addAcademicDirector(Long academicProgramId, Long userId) {
        academicDirectorServicePort.addAcademicDirector(academicProgramId, userId);
    }
}
