package com.microservice.academia.infrastructure.entrypoints.apirest.controller;

import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AcademicProgramRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.AcademicProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/programa-academico")
public class AcademicProgramRestController {
    private final AcademicProgramService academicProgramService;

    @PostMapping
    public ResponseEntity<Void> createProgramaAcademico(@RequestBody AcademicProgramRequestDto academicProgramRequestDto) {
        this.academicProgramService.createAcademicProgram(academicProgramRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
