package com.microservice.academia.infrastructure.entrypoints.apirest;

import com.microservice.academia.domain.model.model.ProgramaAcademico;
import com.microservice.academia.domain.usecase.SaveProgramaAcademicoUseCase;
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

    @PostMapping
    public ResponseEntity<ProgramaAcademico> saveProgramaAcademico(@RequestBody ProgramaAcademico programaAcademico) {
        ProgramaAcademico saveProgramaAcademico = saveProgramaAcademicoUseCase.action(programaAcademico);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProgramaAcademico);
    }
}
