package com.microservice.academia.infrastructure.drivenadapters.jparepository.repository;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.PensumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PensumJpaRepository extends JpaRepository<PensumEntity, Long> {
    @Query(value = "select * from academia.pensum p where id_programa_academico=:id", nativeQuery = true)
    List<PensumEntity> findByProgramAcademicId(@Param("id") Long id);
}
