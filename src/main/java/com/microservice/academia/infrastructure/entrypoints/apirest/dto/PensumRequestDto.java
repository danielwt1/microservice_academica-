package com.microservice.academia.infrastructure.entrypoints.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class PensumRequestDto {
    @NotNull
    private Long programId;

    @NotNull
    private Integer year;
}
