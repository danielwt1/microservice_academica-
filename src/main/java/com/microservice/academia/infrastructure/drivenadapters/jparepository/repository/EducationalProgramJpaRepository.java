package com.microservice.academia.infrastructure.drivenadapters.jparepository.repository;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.EducationalProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalProgramJpaRepository extends JpaRepository<EducationalProgramEntity, Long> {
}
