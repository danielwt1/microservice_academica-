package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.domain.model.ports.spi.CreatePensumPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.PensumEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CreatePensumPersistencePortImpl implements CreatePensumPersistencePort {
    private final PensumJpaRepository pensumJpaRepository;
    private final PensumEntityMapper pensumEntityMapper;

    @Override
    public Pensum createPensum(Pensum pensum) {
        if (pensum == null) {
            throw new IllegalArgumentException("El objeto pensum no puede ser nulo");
        }

        PensumEntity pensumEntity = pensumEntityMapper.pensumToPensumEntity(pensum);
        return pensumEntityMapper.pensumEntityToPensum(pensumJpaRepository.save(pensumEntity));
    }
}

