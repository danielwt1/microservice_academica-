package com.microservice.academia.infrastructure.drivenadapters.jparepository.repository;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicProgramJpaRepository extends JpaRepository<AcademicProgramEntity, Long> {
    @Query(value = "select id_director from programa_academico where id_director=:id", nativeQuery = true)
    Long findIdDirectorOfProgram(@Param("id") Long id);
}
