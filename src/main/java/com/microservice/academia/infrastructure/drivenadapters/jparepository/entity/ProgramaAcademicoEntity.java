package com.microservice.academia.infrastructure.drivenadapters.jparepository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PROGRAMA_ACADEMICO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProgramaAcademicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", unique = true)
    @Size(min = 8, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "id_nivel_educativo")
    private Long idLevelEducation;
}
