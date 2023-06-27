package com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgram.AcademicProgramEntity;
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
@Table(name = "PENSUM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PensumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "año")
    private int year;

    @ManyToOne
    @JoinColumn(name = "id_programa_academico", referencedColumnName = "id")
    private AcademicProgramEntity programId;
}
