package com.microservice.academia.infrastructure.drivenadapters.jparepository.repository;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.ProgramaAcademicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaAcademicoJpaRepository extends JpaRepository<ProgramaAcademicoEntity, Long> {
}
