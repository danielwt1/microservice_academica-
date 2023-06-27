package com.microservice.academia.infrastructure.entrypoints.apirest.controller;

import com.microservice.academia.exceptionhandler.response.ErrorDetails;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AcademicProgramRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.PensumRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.AddAcademicDirectorService;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.CreatePensumService;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.CreateProgramService;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.DeleteProgramService;
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
@RequestMapping("/programas-academicos")
public class AcademicProgramRestController {
    private final CreateProgramService createProgramService;
    private final DeleteProgramService deleteProgramService;
    private final AddAcademicDirectorService addAcademicDirectorService;
    private final CreatePensumService createPensumService;


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
    @PostMapping("/academic-Program")
    public ResponseEntity<Void> createProgramaAcademico(@RequestBody AcademicProgramRequestDto academicProgramRequestDto) {
        this.createProgramService.createAcademicProgram(academicProgramRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    @DeleteMapping("/academic-Program/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        this.deleteProgramService.DeleteAcademicProgram(id);
        return ResponseEntity.noContent().build();
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
    @PatchMapping("/academic-Program/addDirector")
    public ResponseEntity<Void> addAcademicDirector(@RequestParam("programID") Long programID, @RequestParam("userId") Long userId) {
        this.addAcademicDirectorService.addAcademicDirector(programID, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Le permite a un usuario de tipo Decano la creación de un pensum",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Se creo con éxito un pensum"),
                    @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "401", description = "El usuario no está autenticado, o el token esta incorrecto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class)))
            }
    )
    @PostMapping("/pensum")
    public ResponseEntity<Void> createPensum(@RequestBody PensumRequestDto pensumRequestDto) {
        this.createPensumService.createPensum(pensumRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
