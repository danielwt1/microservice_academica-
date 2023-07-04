package com.microservice.academia.infrastructure.entrypoints.apirest.controller;

import com.microservice.academia.exceptionhandler.response.ErrorDetails;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.request.AcademicProgramRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.impl.AcademyProgramServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academia_dev")
public class AcademicProgramController {
    private final AcademyProgramServiceImpl academyProgramService;

    @Operation(summary = "Le permite a un usuario de tipo Decano la creación de un programa académico",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Se creo con éxito un programa académico"),
                    @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "401", description = "El usuario no está autenticado, o el token esta incorrecto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class)))
            }
    )
    @PostMapping("/program")
    public ResponseEntity<Void> createProgram(@RequestBody AcademicProgramRequestDto academicProgramRequestDto) {
        this.academyProgramService.createAcademicProgram(academicProgramRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Le permite a un usuario de tipo Decano la asignación de un director a un programa academico",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Se asigno un director con éxito"),
                    @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "401", description = "El usuario no está autenticado, o el token esta incorrecto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class)))
            }
    )
    @PatchMapping("/programs/addDirector")
    public ResponseEntity<Void> addAcademicDirector(@RequestParam("programId") Long programID, @RequestParam("userId") Long userId) {
        this.academyProgramService.assignAcademicDirector(programID, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Le permite a un usuario de tipo Decano la eliminación de un programa académico",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Se elimino con éxito un programa académico"),
                    @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "401", description = "El usuario no está autenticado, o el token esta incorrecto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class)))
            }
    )
    @DeleteMapping("/programs/delete/{id}")
    public ResponseEntity<Void> deleteProgramById(@PathVariable("id") Long id) {
        this.academyProgramService.deleteAcademicProgram(id);
        return ResponseEntity.noContent().build();
    }


}
