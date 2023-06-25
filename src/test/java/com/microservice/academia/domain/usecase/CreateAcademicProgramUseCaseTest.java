package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.AcademicProgram;
import com.microservice.academia.domain.model.ports.spi.AcademicProgramPersistencePort;
import com.microservice.academia.mock.programaAcademico.AcademicProgramMockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class CreateAcademicProgramUseCaseTest {
    @InjectMocks
    private CreateAcademicProgramUseCase createAcademicProgramUseCase;

    @Mock
    private AcademicProgramPersistencePort academicProgramPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a valid programa academico, when saving the programa, then it should be saved successfully")
    void testSaveProgramaAcademico_Success() {
        // Arrange
        Long programId = 1L;
        AcademicProgram academicProgram = AcademicProgramMockData.getProgram(programId);
        when(academicProgramPersistencePort.createAcademicProgram(academicProgram)).thenReturn(academicProgram);

        // Act
        AcademicProgram result = createAcademicProgramUseCase.createAcademicProgram(academicProgram);

        // Assert
        assertEquals(academicProgram, result);
    }

    @Test
    @DisplayName("Given an invalid programa academico, when saving the programa, then it should throw an exception")
    void testSaveProgramaAcademico_TechnicalError() {
        // Arrange
        Long programId = 1L;
        AcademicProgram program = AcademicProgramMockData.getProgram(programId);

        // Act & Assert
        doThrow(AcademiaExceptions.class).when(academicProgramPersistencePort).createAcademicProgram(program);
        assertThrows(AcademiaExceptions.class, () -> createAcademicProgramUseCase.createAcademicProgram(program));
    }
}