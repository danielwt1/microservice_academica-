package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.UserNotFoundException;
import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.domain.model.model.userservice.UserModel;
import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions.AcademicProgramNotFoundException;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions.ProgramDeletionNotAllowedException;
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
        AcademicProgramEntity academicProgramEntity = academicProgramEntityMapper.programaToProgramaEntity(academicProgram);
        if (academicProgramJpaRepository.findById(academicProgram.getEducationLevel().getId()).isEmpty()) {
            throw new AcademicProgramNotFoundException("debe existir un nivel educativo", HttpStatus.BAD_REQUEST);
        }
        validateAcademicProgram(academicProgram);
        return academicProgramEntityMapper.programaEntityToPrograma(academicProgramJpaRepository.save(academicProgramEntity));
    }

    @Override
    public void deleteAcademicProgram(Long idProgram) {
        AcademicProgramEntity programEntity = validateProgramDeletion(idProgram);
        academicProgramJpaRepository.delete(programEntity);
    }

    private AcademicProgramEntity validateProgramDeletion(Long idProgram) {
        AcademicProgramEntity programEntity = academicProgramJpaRepository.findById(idProgram)
                .orElseThrow(() -> new AcademicProgramNotFoundException("Programa con : " + idProgram + "no encontrado", HttpStatus.NOT_FOUND));
        if (programEntity.getTypeUserId() != null) {
            throw new ProgramDeletionNotAllowedException("No Es posible eliminar un programa con un director asignado", HttpStatus.BAD_REQUEST);
        }
        List<PensumEntity> validateAssignmentPensum = pensumJpaRepository.findByProgramAcademicId(idProgram);
        if (!validateAssignmentPensum.isEmpty()) {
            throw new ProgramDeletionNotAllowedException("No es posible eliminar un programa con un pensum asignado", HttpStatus.BAD_REQUEST);
        }
        return programEntity;
    }

    private AcademicProgramEntity getAcademicProgramById(Long academicProgramId) {
        return academicProgramJpaRepository.findById(academicProgramId)
                .orElseThrow(() -> new AcademicProgramNotFoundException("Programa académico no encontrado", HttpStatus.NOT_FOUND));
    }

    private void validateRole(UserModel userModel) {
        if (!userModel.getTypeUserName().equals("DIRECTOR")) {
            String role = userModel.getTypeUserName();
            throw new UserNotFoundException("No es posible asignar el role " + role + " a un programa academico", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateAssignment(Long idDirector) {
        Long userId = academicProgramJpaRepository.findIdDirectorOfProgram(idDirector);
        if (userId != null) {
            throw new UserNotFoundException("Un director no puede ser asignado a más de un programa", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateAcademicProgram(AcademicProgram academicProgram) {
        String programName = academicProgram.getName();
        String programDescription = academicProgram.getDescription();
        if (academicProgramJpaRepository.existsByName(programName)) {
            throw new UserNotFoundException("El nombre del programa académico ya existe"
                    , HttpStatus.BAD_REQUEST);
        }
        if (programName.length() < 8 || programName.length() > 40) {
            throw new UserNotFoundException("El nombre del programa académico debe tener una longitud de 8 a 40 caracteres"
                    , HttpStatus.BAD_REQUEST);
        }
        if (programDescription.length() < 20 || programDescription.length() > 200) {
            throw new UserNotFoundException("La descripcion debe tener una longitud de 20 a 200 caracteres"
                    , HttpStatus.BAD_REQUEST);
        }

        if (programName.matches("^[a-zA-Z]+$")) {
            throw new UserNotFoundException("El nombre del programa académico solo puede contener letras"
                    , HttpStatus.BAD_REQUEST);
        }
    }
}
