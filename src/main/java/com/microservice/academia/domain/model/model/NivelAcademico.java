package com.microservice.academia.domain.model.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class NivelAcademico {
    private Long id;
    private String name;
    private String description;
}
