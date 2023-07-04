package com.microservice.academia.domain.model.model.academy;

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
public class Assignment {
    private Long id;
    private Pensum pensumId;
    private String name;
    private String description;
    private Assignment preAssignmentId;
}
