package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteAcademicProgramTest {
    @Mock
    private AcademyPersistencePort academyPersistencePort;

    @InjectMocks
    private DeleteAcademicProgram deleteAcademicProgram;

    @BeforeEach
    void setUp() {
        deleteAcademicProgram = new DeleteAcademicProgram(academyPersistencePort);
    }

    @Test
    @DisplayName("Given an academy program ID, when deleting the academy program, " +
            "then it should invoke the delete persistence port")
    void testDeleteAcademicProgram() {
        // Arrange
        Long idProgram = 1L;

        // Act
        deleteAcademicProgram.action(idProgram);

        // Assert
        verify(academyPersistencePort).deleteAcademicProgram(idProgram);
    }

    @Test
    @DisplayName("Given an academy program ID, when deleting the academy program then technical erro")
    void testDeleteTechnicalError() {
        // Arrange
        Long idProgram = 1L;
        // Act & Assert
        doThrow(RuntimeException.class).when(academyPersistencePort).deleteAcademicProgram(idProgram);
        assertThrows(RuntimeException.class, () -> deleteAcademicProgram.action(idProgram));
    }
}