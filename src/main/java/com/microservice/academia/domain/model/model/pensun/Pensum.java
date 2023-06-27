package com.microservice.academia.domain.model.model.pensun;

import com.microservice.academia.domain.model.model.AcademicProgram.AcademicProgram;
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
public class Pensum {
    private Long id;
    private int year;
    private AcademicProgram programId;
}
