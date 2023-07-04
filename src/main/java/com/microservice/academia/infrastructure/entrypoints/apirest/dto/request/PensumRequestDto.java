package com.microservice.academia.infrastructure.entrypoints.apirest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "DTO REQUEST para a un pensum",
        name = "PensumRequestDto")
public class PensumRequestDto {
    @Schema(description = "Id del programa academico", example = "1")
    private Long programId;

    @Schema(description = "año del programa academico", example = "2023")
    private Integer year;
}
