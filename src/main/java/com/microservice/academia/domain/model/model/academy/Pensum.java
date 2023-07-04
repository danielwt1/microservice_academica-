package com.microservice.academia.domain.model.model.academy;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Pensum {
    private Long id;
    private int year;
    private AcademicProgram programId;
    private List<Assignment> assignments;
}
