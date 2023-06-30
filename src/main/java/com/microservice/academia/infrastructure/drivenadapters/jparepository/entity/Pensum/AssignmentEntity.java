package com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MATERIA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "id_pensum")
    private Long pensumId;

    @OneToOne
    @JoinColumn(name = "id_materia_prerrequisito", referencedColumnName = "id")
    private AssignmentEntity preAssignmentId;
}
