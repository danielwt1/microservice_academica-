package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.User.UserModel;
import com.microservice.academia.domain.model.ports.spi.AddAcademicDirectorPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgram.AcademicProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AcademicProgramJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.userservice.services.UserModelServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class AddAcademicProgramPersistencePortImpl implements AddAcademicDirectorPersistencePort {
    private final AcademicProgramJpaRepository academicProgramJpaRepository;
    private final UserModelServicesImpl userModelServices;

    @Override
    @Transactional
    public void addAcademicDirector(Long academicProgramId, Long idDirector) {
        AcademicProgramEntity programEntity = getAcademicProgramById(academicProgramId);
        UserModel directorUser = userModelServices.getUserById(idDirector);
        validateRole(directorUser);
        validateAssignment(idDirector);
        programEntity.setTypeUserId(directorUser.getTypeUserId());
        academicProgramJpaRepository.save(programEntity);
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