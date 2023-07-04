package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.exeptions.UserNotFoundException;
import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.domain.model.model.userservice.UserModel;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.AcademicProgramEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.AcademicProgramEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AcademicProgramJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.userservice.services.UserModelServicesImpl;
import com.microservice.academia.mock.programaAcademico.AcademicProgramMockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AcademicProgramImplTest {
    @Mock
    private AcademicProgramJpaRepository academicProgramJpaRepository;

    @Mock
    private AcademicProgramEntityMapper academicProgramEntityMapper;

    @Mock
    private PensumJpaRepository pensumJpaRepository;

    @Mock
    private UserModelServicesImpl userModelServices;

    @InjectMocks
    private AcademicProgramImpl academicProgramImpl;

    @BeforeEach
    void setUp() {
        academicProgramImpl = new AcademicProgramImpl(
                academicProgramJpaRepository,
                academicProgramEntityMapper,
                pensumJpaRepository,
                userModelServices
        );
    }

    @Test
    @Disabled
    void assignAcademicDirector() {
        Long academicProgramId = 1L;
        Long directorId = 100L;

        AcademicProgramEntity programEntity = new AcademicProgramEntity();
        programEntity.setId(academicProgramId);

        UserModel directorUser = UserModel.builder().id(directorId).typeUserName("DIRECTOR").build();


        // Simular el comportamiento de las dependencias
        when(academicProgramJpaRepository.findById(academicProgramId)).thenReturn(Optional.of(programEntity));
        when(userModelServices.getUserById(directorId)).thenReturn(directorUser);

        // Llamar al método que queremos probar
        academicProgramImpl.assignAcademicDirector(academicProgramId, directorId);

        // Verificar que los métodos de las dependencias fueron llamados con los argumentos correctos
        verify(academicProgramJpaRepository).findById(academicProgramId);
        verify(userModelServices).getUserById(directorId);
        verify(academicProgramJpaRepository).save(programEntity);

        // Asegurarse de que el tipo de usuario se haya establecido correctamente
        Assertions.assertEquals(directorUser.getTypeUserId(), programEntity.getTypeUserId());

    }

    @Test
    void createAcademicProgram() {
        // Arrange
        Long programId = 1L;
        AcademicProgram academicProgram = AcademicProgramMockData.getProgram(programId);

        AcademicProgramEntity academicProgramEntity = new AcademicProgramEntity();
        when(academicProgramEntityMapper.programaToProgramaEntity(academicProgram)).thenReturn(academicProgramEntity);
        when(academicProgramJpaRepository.save(academicProgramEntity)).thenReturn(academicProgramEntity);
        when(academicProgramEntityMapper.programaEntityToPrograma(academicProgramEntity)).thenReturn(academicProgram);

        // Act
        AcademicProgram createdProgram = academicProgramImpl.createAcademicProgram(academicProgram);

        // Assert
        assertNotNull(createdProgram);
        assertEquals(academicProgram, createdProgram);
        verify(academicProgramJpaRepository, times(1)).save(academicProgramEntity);
    }

    @Test
    void createAcademicProgramWithInvalidEducationLevel() {
        // Arrange
        Long programId = 1L;
        AcademicProgram academicProgram = AcademicProgramMockData.getProgram(programId);
        academicProgram.getEducationLevel().setId(null);

        // Act and Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> academicProgramImpl.createAcademicProgram(academicProgram));
        assertEquals("debe existir un nivel educativo", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        verify(academicProgramJpaRepository, never()).save(any(AcademicProgramEntity.class));
    }

    @Test
    void testDeleteAcademicProgram_NotFound() {
        // Arrange
        Long idProgram = 1L;
        when(academicProgramJpaRepository.findById(idProgram)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> academicProgramImpl.deleteAcademicProgram(idProgram));
        verify(academicProgramJpaRepository, never()).delete(any());
    }

    @Test
    void testDeleteAcademicProgram() {
        Long idProgram = 1L;

        AcademicProgramEntity programEntity = new AcademicProgramEntity();
        when(academicProgramJpaRepository.findById(idProgram)).thenReturn(Optional.of(programEntity));
        when(pensumJpaRepository.findByProgramAcademicId(idProgram)).thenReturn(Collections.emptyList());

        academicProgramImpl.deleteAcademicProgram(idProgram);

        verify(academicProgramJpaRepository, times(1)).delete(programEntity);
    }

    @Test
    void testDeleteAcademicProgram_AssignedPensum() {
        // Arrange
        Long idProgram = 1L;
        AcademicProgramEntity programEntity = new AcademicProgramEntity();
        when(academicProgramJpaRepository.findById(idProgram)).thenReturn(Optional.of(programEntity));
        when(pensumJpaRepository.findByProgramAcademicId(idProgram)).thenReturn(Collections.singletonList(new PensumEntity()));

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> academicProgramImpl.deleteAcademicProgram(idProgram));
        verify(academicProgramJpaRepository, never()).delete(any());
    }

    @Test
    void testDeleteAcademicProgram_AssignedDirector() {
        // Arrange
        Long idProgram = 1L;
        AcademicProgramEntity programEntity = new AcademicProgramEntity();
        programEntity.setTypeUserId(1L);
        when(academicProgramJpaRepository.findById(idProgram)).thenReturn(Optional.of(programEntity));

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> academicProgramImpl.deleteAcademicProgram(idProgram));
        verify(academicProgramJpaRepository, never()).delete(any());
    }
}