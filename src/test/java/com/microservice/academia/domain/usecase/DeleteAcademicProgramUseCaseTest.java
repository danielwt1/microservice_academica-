package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.ports.spi.DeleteProgramPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class DeleteAcademicProgramUseCaseTest {
    @Mock
    private DeleteProgramPersistencePort deleteProgramPersistencePort;

    @InjectMocks
    private DeleteAcademicProgramUseCase deleteAcademicProgramUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteAcademicProgramUseCase = new DeleteAcademicProgramUseCase(deleteProgramPersistencePort);
    }

    @Test
    void testDeleteAcademicProgram() {
        // Arrange
        Long idProgram = 1L;

        // Act
        deleteAcademicProgramUseCase.DeleteAcademicProgram(idProgram);

        // Assert
        verify(deleteProgramPersistencePort).DeleteAcademicProgram(idProgram);
    }
}