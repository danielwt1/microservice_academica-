package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.domain.model.ports.repositories.PensumPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AssignmentEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions.PensumNotFoundException;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.PensumEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AssignmentJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PensumImpl implements PensumPersistencePort {
    private final PensumJpaRepository pensumJpaRepository;
    private final AssignmentJpaRepository assignmentJpaRepository;
    private final PensumEntityMapper pensumEntityMapper;

    @Override
    public Pensum createPensum(Pensum pensum) {
        if (Boolean.TRUE.equals(validateAssignment(pensum))) {
            throw new PensumNotFoundException("No es posible asignar un Pensum a un programa para un mismo año", HttpStatus.BAD_REQUEST);
        }
        PensumEntity pensumEntity = pensumEntityMapper.pensumToPensumEntity(pensum);
        return pensumEntityMapper.pensumEntityToPensum(pensumJpaRepository.save(pensumEntity));
    }

    @Override
    public void deletePensum(Long pensumId) {
        List<AssignmentEntity> assignmentEntity = assignmentJpaRepository.findByIdPensum(pensumId);
        if (!assignmentEntity.isEmpty()) {
            throw new PensumNotFoundException("No es posible eliminar un pensum con materias asociadas", HttpStatus.BAD_REQUEST);
        }
        pensumJpaRepository.deleteById(pensumId);
    }

    private Boolean validateAssignment(Pensum pensum) {
        int year = pensum.getYear();
        Long programID = pensum.getProgramId().getId();
        List<PensumEntity> pensumEntityList = pensumJpaRepository.findByProgramAcademicId(programID);
        return pensumEntityList.stream().anyMatch(entity -> entity.getYear() == year);
    }
}
