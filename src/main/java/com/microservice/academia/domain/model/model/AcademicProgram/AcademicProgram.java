package com.microservice.academia.domain.model.model.AcademicProgram;

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
public class AcademicProgram {
    private Long id;
    private String name;
    private String description;
    private EducationLevel educationLevel;
}
