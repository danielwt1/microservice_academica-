package com.microservice.academia.infrastructure.entrypoints.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AssignmentRequestDto {
    private Long pensumId;
    private String name;
    private String description;
    private Long preAssignmentId;
}
