package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.ports.spi.DeletePensumPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.AssignmentEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.Pensum.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AssignmentJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DeletePensumPersistenceImpl implements DeletePensumPersistencePort {
    private final PensumJpaRepository pensumJpaRepository;
    private final AssignmentJpaRepository assignmentJpaRepository;

    @Override
    public void deletePensum(Long idPensum) {
      List<AssignmentEntity> assignmentEntity = assignmentJpaRepository.findByIdPensum(idPensum);
      if (!assignmentEntity.isEmpty()){
          throw new AcademiaExceptions("No es posible eliminar un pensum con materias asociadas", HttpStatus.BAD_REQUEST);
      }
       pensumJpaRepository.deleteById(idPensum);
    }
}
