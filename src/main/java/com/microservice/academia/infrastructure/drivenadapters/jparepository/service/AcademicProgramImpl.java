package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.domain.model.model.userservice.UserModel;
import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.AcademicProgramEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AcademicProgramJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.userservice.services.UserModelServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AcademicProgramImpl implements AcademyPersistencePort {
    private final AcademicProgramJpaRepository academicProgramJpaRepository;
    private final AcademicProgramEntityMapper academicProgramEntityMapper;
    private final PensumJpaRepository pensumJpaRepository;
    private final UserModelServicesImpl userModelServices;

    @Override
    public void assignAcademicDirector(Long academicProgramId, Long directorId) {
        AcademicProgramEntity programEntity = getAcademicProgramById(academicProgramId);
        UserModel directorUser = userModelServices.getUserById(directorId);
        validateRole(directorUser);
        validateAssignment(directorId);
        programEntity.setTypeUserId(directorUser.getTypeUserId());
        academicProgramJpaRepository.save(programEntity);
    }

    @Override
    public AcademicProgram createAcademicProgram(AcademicProgram academicProgram) {
        if (academicProgram.getEducationLevel().getId() == null) {
            throw new AcademiaExceptions("debe existir un nivel educativo", HttpStatus.BAD_REQUEST);
        }
        AcademicProgramEntity academicProgramEntity = academicProgramEntityMapper.programaToProgramaEntity(academicProgram);
        return academicProgramEntityMapper.programaEntityToPrograma(academicProgramJpaRepository.save(academicProgramEntity));
    }

    @Override
    public void deleteAcademicProgram(Long idProgram) {
        AcademicProgramEntity programEntity = validateProgramDeletion(idProgram);
        academicProgramJpaRepository.delete(programEntity);
    }

    private AcademicProgramEntity validateProgramDeletion(Long idProgram) {
        AcademicProgramEntity programEntity = academicProgramJpaRepository.findById(idProgram)
                .orElseThrow(() -> new AcademiaExceptions("Programa con : " + idProgram + "no encontrado", HttpStatus.NOT_FOUND));
        if (programEntity.getTypeUserId() != null) {
            throw new AcademiaExceptions("No Es posible eliminar un programa con un director asignado", HttpStatus.BAD_REQUEST);
        }
        List<PensumEntity> validateAssignmentPensum = pensumJpaRepository.findByProgramAcademicId(idProgram);
        if (!validateAssignmentPensum.isEmpty()) {
            throw new AcademiaExceptions("No es posible eliminar un programa con un pensum asignado", HttpStatus.BAD_REQUEST);
        }
        return programEntity;
    }

    private AcademicProgramEntity getAcademicProgramById(Long academicProgramId) {
        return academicProgramJpaRepository.findById(academicProgramId)
                .orElseThrow(() -> new AcademiaExceptions("Programa académico no encontrado", HttpStatus.NOT_FOUND));
    }

    private void validateRole(UserModel userModel) {
        if (!userModel.getTypeUserName().equals("DIRECTOR")) {
            String role = userModel.getTypeUserName();
            throw new AcademiaExceptions("No es posible asignar el role " + role + " a un programa academico", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateAssignment(Long idDirector) {
        Long userId = academicProgramJpaRepository.findIdDirectorOfProgram(idDirector);
        if (userId != null) {
            throw new AcademiaExceptions("Un director no puede ser asignado a más de un programa", HttpStatus.BAD_REQUEST);
        }
    }
}
