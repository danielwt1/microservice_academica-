package com.microservice.academia.infrastructure.entrypoints.apirest.controller;

import com.microservice.academia.exceptionhandler.response.ErrorDetails;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.request.AssignmentRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.response.AssignmentResponseDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.impl.AssignmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academia_dev")
public class AssignmentRestController {
    private final AssignmentServiceImpl assignmentService;

    @Operation(summary = "Le permite a un usuario de tipo Director agregar una materia en un pensum",
            responses = {
                    @ApiResponse(responseCode = "204", description = "se agrega materia con exito"),
                    @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "401", description = "El usuario no está autenticado, o el token esta incorrecto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class)))
            }
    )
    @PostMapping("/pensum/addAssignments")
    public ResponseEntity<Void> addAssignment(@RequestBody AssignmentRequestDto assignmentRequestDto) {
        this.assignmentService.addAssignmentServicePort(assignmentRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Le permite consultar el detalle de una materia por su id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "se agrega materia con exito"),
                    @ApiResponse(responseCode = "500", description = "A business logic error occurred",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class))),
                    @ApiResponse(responseCode = "401", description = "El usuario no está autenticado, o el token esta incorrecto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(type = "object", implementation = ErrorDetails.class)))
            }
    )
    @GetMapping("/pensum/assignments")
    public ResponseEntity<AssignmentResponseDto> getAssignmentById(@RequestParam("assignmentId") Long assignmentId) {
        AssignmentResponseDto response = this.assignmentService.getAssignmentServicePort(assignmentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
