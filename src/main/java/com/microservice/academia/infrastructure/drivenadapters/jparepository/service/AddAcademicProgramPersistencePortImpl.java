package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.User.UserModel;
import com.microservice.academia.domain.model.ports.spi.AddAcademicDirectorPersistencePort;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.EducationalProgramJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.userservice.services.UserModelServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class AddAcademicProgramPersistencePortImpl implements AddAcademicDirectorPersistencePort {
    private final EducationalProgramJpaRepository educationalProgramJpaRepository;
    private final UserModelServicesImpl userModelServices;

    @Override
    @Transactional
    public void addAcademicDirector(Long academicProgramId, Long userId) {
        AcademicProgramEntity programEntity = educationalProgramJpaRepository.findById(academicProgramId)
                .orElseThrow(() -> new AcademiaExceptions("Programa academico no encontrado", HttpStatus.NOT_FOUND));
        UserModel directorUser = userModelServices.getUserById(userId);
        validateRole(directorUser);
        programEntity.setTypeUserId(directorUser.getTypeUserId());
        educationalProgramJpaRepository.save(programEntity);
    }

    private void validateRole(UserModel userModel) {
        if (!userModel.getTypeUserName().equals("DIRECTOR")) {
            throw new AcademiaExceptions("Rol no encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
