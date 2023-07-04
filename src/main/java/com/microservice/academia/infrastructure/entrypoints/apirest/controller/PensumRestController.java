package com.microservice.academia.infrastructure.entrypoints.apirest.controller;

import com.microservice.academia.exceptionhandler.response.ErrorDetails;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.request.PensumRequestDto;
import com.microservice.academia.infrastructure.entrypoints.apirest.service.impl.PensumServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academia_dev")
public class PensumRestController {
    private final PensumServiceImpl pensumService;

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
        this.pensumService.createPensum(pensumRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Le permite a un usuario de tipo Decano la eliminación de un pensum",
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
    @DeleteMapping("/pensum/delete/{id}")
    public ResponseEntity<Void> deletePensumById(@PathVariable("id") Long id) {
        this.pensumService.deletePensum(id);
        return ResponseEntity.noContent().build();
    }

}
