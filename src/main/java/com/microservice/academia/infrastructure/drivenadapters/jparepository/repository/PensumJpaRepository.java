package com.microservice.academia.infrastructure.drivenadapters.jparepository.repository;

import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.PensumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PensumJpaRepository extends JpaRepository<PensumEntity, Long> {
}
