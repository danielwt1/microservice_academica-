package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.domain.model.ports.spi.CreatePensumPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.PensumEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CreatePensumPersistencePortImpl implements CreatePensumPersistencePort {
    private final PensumJpaRepository pensumJpaRepository;
    private final PensumEntityMapper pensumEntityMapper;

    @Override
    public Pensum createPensum(Pensum pensum) {
        if (Boolean.TRUE.equals(validateAssignment(pensum))) {
            throw new AcademiaExceptions("No es posible asignar un Pensum a un programa para un mismo año", HttpStatus.BAD_REQUEST);
        }
        PensumEntity pensumEntity = pensumEntityMapper.pensumToPensumEntity(pensum);
        return pensumEntityMapper.pensumEntityToPensum(pensumJpaRepository.save(pensumEntity));
    }

    private Boolean validateAssignment(Pensum pensum) {
        int year = pensum.getYear();
        Long programID = pensum.getProgramId().getId();
        List<PensumEntity> pensumEntityList = pensumJpaRepository.findByProgramAcademicId(programID);
        return pensumEntityList.stream().anyMatch(entity -> entity.getYear() == year);
    }
}

