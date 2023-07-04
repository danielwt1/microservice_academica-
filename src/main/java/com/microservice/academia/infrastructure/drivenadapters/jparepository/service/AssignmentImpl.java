package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.model.model.academy.Assignment;
import com.microservice.academia.domain.model.ports.repositories.AssignmentPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AssignmentEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions.InvalidAssignmentException;
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
public class AssignmentImpl implements AssignmentPersistencePort {
    private final AssignmentJpaRepository assignmentJpaRepository;
    private final PensumJpaRepository pensumJpaRepository;
    private final AssignmentEntityMapper assignmentEntityMapper;

    @Override
    @Transactional
    public void addAssignment(Assignment assignment) {
        AssignmentEntity assignmentEntity = assignmentEntityMapper.assignmentToEntity(assignment);
        validatePrerrequisito(assignmentEntity);
        validatePensum(assignmentEntity);
        validateAssignment(assignmentEntity);
        assignmentJpaRepository.save(assignmentEntity);
    }

    private void validatePrerrequisito(AssignmentEntity assignmentEntity) {
        Optional<AssignmentEntity> prerrequisito = assignmentJpaRepository.findById(assignmentEntity.getPreAssignmentId().getId());
        if (prerrequisito.isPresent()) {
            Long prerrequisitoPensum = prerrequisito.get().getPensumId();
            if (!Objects.equals(prerrequisitoPensum, assignmentEntity.getPensumId())) {
                throw new InvalidAssignmentException("El pensum de la materia prerrequisito no es igual al pensum de la materia padre", HttpStatus.BAD_REQUEST);
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

    private void validateAssignment(AssignmentEntity assignmentEntity) {
        String assignmentName = assignmentEntity.getName();
        String assignmentDescription = assignmentEntity.getDescription();

        if (assignmentName.length() < 8 || assignmentName.length() > 40) {
            throw new InvalidAssignmentException("El nombre de la materia debe tener una longitud de 2 a 30 caracteres"
                    , HttpStatus.BAD_REQUEST);
        }
        if (assignmentDescription.length() < 20 || assignmentDescription.length() > 200) {
            throw new InvalidAssignmentException("La descripcion debe tener una longitud de 5 a 200 caracteres"
                    , HttpStatus.BAD_REQUEST);
        }
    }
}
