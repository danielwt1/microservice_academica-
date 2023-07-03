package com.microservice.academia.infrastructure.drivenadapters.jparepository.repository;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.AssignmentEntity;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentJpaRepository extends JpaRepository<AssignmentEntity, Long> {
    @Query(value = "select *  from academia.materia m where id_pensum=:id", nativeQuery = true)
    List<AssignmentEntity> findByIdPensum(@Param("id") Long id);

    /*
    @Query(value = "SELECT pa.id AS programId, pe.id AS pensumId, m.id AS assignmentId, m.nombre AS assignmentName, m.descripcion AS assignmentDescription, m.id_materia_prerrequisito AS preAssignmentId " +
            "FROM academia.programa_academico pa " +
            "JOIN academia.pensum pe ON pa.id = pe.id_programa_academico " +
            "JOIN academia.materia m ON pe.id = m.id_pensum " +
            "WHERE m.id = :idAssignment", nativeQuery = true)
    AssignmentResponseDto findAssignmentById(@Param("idAssignment") Long id);

     */

    @Query("SELECT NEW com.microservice.academia.infrastructure.entrypoints.apirest.dto.AssignmentResponseDto(p.programId.id, m.pensumId, m.id, m.name, m.description, m.preAssignmentId.id) " +
            "FROM AssignmentEntity m " +
            "JOIN PensumEntity p ON m.pensumId = p.id " +
            "WHERE m.id = :assignmentId")
    AssignmentResponseDto findAssignmentDetailsByMateriaId(@Param("assignmentId") Long materiaId);

}
