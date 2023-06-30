package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.pensun.Assignment;
import com.microservice.academia.domain.model.ports.spi.AddAssignmentPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.AssignmentEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.AssignmentEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AssignmentJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AddAssignmentPersistenceImpl implements AddAssignmentPersistencePort {
    private final AssignmentJpaRepository assignmentJpaRepository;
    private final PensumJpaRepository pensumJpaRepository;
    private final AssignmentEntityMapper assignmentEntityMapper;

    @Override
    @Transactional
    public void addAssignmentServicePort(Assignment assignment) {
        AssignmentEntity assignmentEntity = assignmentEntityMapper.assignmentToEntity(assignment);
        validatePrerrequisito(assignmentEntity);
        validatePensum(assignmentEntity);
        assignmentJpaRepository.save(assignmentEntity);
    }

    private void validatePrerrequisito(AssignmentEntity assignmentEntity) {
        Optional<AssignmentEntity> prerrequisito = assignmentJpaRepository.findById(assignmentEntity.getPreAssignmentId().getId());
        if (prerrequisito.isPresent()) {
            Long prerrequisitoPensum = prerrequisito.get().getPensumId();
            if (!Objects.equals(prerrequisitoPensum, assignmentEntity.getPensumId())) {
                throw new AcademiaExceptions("El pensum de la materia prerrequisito no es igual al pensum de la materia padre", HttpStatus.BAD_REQUEST);
            }
        } else {
            assignmentEntity.setPreAssignmentId(null);
        }
    }

    private void validatePensum(AssignmentEntity assignmentEntity) {
        Optional<PensumEntity> pensum = pensumJpaRepository.findById(assignmentEntity.getPensumId());
        if (pensum.isEmpty()) {
            assignmentEntity.setPensumId(null);
        }
    }
}
