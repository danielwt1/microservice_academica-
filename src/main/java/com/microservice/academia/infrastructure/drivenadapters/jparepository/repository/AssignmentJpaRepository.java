package com.microservice.academia.infrastructure.drivenadapters.jparepository.repository;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AssignmentEntity;
import com.microservice.academia.infrastructure.entrypoints.apirest.dto.response.AssignmentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentJpaRepository extends JpaRepository<AssignmentEntity, Long> {
    @Query(value = "select *  from academia.materia m where id_pensum=:id", nativeQuery = true)
    List<AssignmentEntity> findByIdPensum(@Param("id") Long id);

    @Query("SELECT NEW com.microservice.academia.infrastructure.entrypoints.apirest.dto.response.AssignmentResponseDto(p.programId.id, m.pensumId, m.id, m.name, m.description, m.preAssignmentId.id) " +
            "FROM AssignmentEntity m " +
            "JOIN PensumEntity p ON m.pensumId = p.id " +
            "WHERE m.id = :assignmentId")
    AssignmentResponseDto findAssignmentDetailsByMateriaId(@Param("assignmentId") Long materiaId);
}
