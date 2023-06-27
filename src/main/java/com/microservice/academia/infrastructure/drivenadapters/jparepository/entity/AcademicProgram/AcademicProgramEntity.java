package com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgram;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROGRAMA_ACADEMICO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AcademicProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", unique = true)
    private String name;

    @Column(name = "descripcion")
    private String description;

    @ManyToOne
    @JoinColumn(name = "nivel_educativo_id", referencedColumnName = "id")
    private EducationalLevelEntity educationLevel;

    @Column(name = "id_director")
    private Long typeUserId;
}
