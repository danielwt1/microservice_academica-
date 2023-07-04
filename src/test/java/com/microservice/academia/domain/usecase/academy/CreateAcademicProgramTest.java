package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import com.microservice.academia.mock.programaAcademico.AcademicProgramMockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateAcademicProgramTest {
    @Mock
    private AcademyPersistencePort academyPersistencePort;

    @InjectMocks
    private CreateAcademicProgram createAcademicProgram;

    @BeforeEach
    void setUp() {
        createAcademicProgram = new CreateAcademicProgram(academyPersistencePort);
    }

    @Test
    @DisplayName("Given a valid programa academico, when saving the programa, then it should be saved successfully")
    void testCreateProgramaAcademicoSuccess() {
        // Arrange
        Long programId = 1L;
        AcademicProgram academicProgram = AcademicProgramMockData.getProgram(programId);

        // Act
        createAcademicProgram.action(academicProgram);

        // Assert
        verify(academyPersistencePort, times(1)).createAcademicProgram(academicProgram);
    }

    @Test
    @DisplayName("Given an invalid programa academico, when saving the programa, then it should throw an exception")
    void testCreateProgramaAcademicoTechnicalError() {
        // Arrange
        Long programId = 1L;
        AcademicProgram academicProgram = AcademicProgramMockData.getProgram(programId);
        createAcademicProgram.action(academicProgram);

        // Act & Assert
        doThrow(AcademiaExceptions.class).when(academyPersistencePort).createAcademicProgram(academicProgram);
        assertThrows(AcademiaExceptions.class, () -> createAcademicProgram.action(academicProgram));
    }
}