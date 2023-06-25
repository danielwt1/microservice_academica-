package com.microservice.academia.infrastructure.entrypoints.apirest;

import com.microservice.academia.domain.model.model.ProgramaAcademico;
import com.microservice.academia.domain.usecase.SaveProgramaAcademicoUseCase;
import com.microservice.academia.infrastructure.entrypoints.apirest.rest.dto.ProgramaAcademicoRequestDTO;
import com.microservice.academia.infrastructure.entrypoints.apirest.rest.mapper.ProgramaAcademicoRequestMapper;
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
public class ProgramaAcademicoController {
    private final SaveProgramaAcademicoUseCase saveProgramaAcademicoUseCase;
    private final ProgramaAcademicoRequestMapper requestMapper;

    @PostMapping
    public ResponseEntity<Void> saveProgramaAcademico(@RequestBody ProgramaAcademicoRequestDTO programaAcademicoRequestDTO) {
        ProgramaAcademico programaAcademico = requestMapper.toProgramaAcademico(programaAcademicoRequestDTO);
        saveProgramaAcademicoUseCase.action(programaAcademico);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }
}
