package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.ProgramaAcademico;
import com.microservice.academia.domain.model.ports.api.ProgramaAcademicoRepository;
import com.microservice.academia.mock.programaAcademico.ProgramaAcademicoMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class SaveProgramaAcademicoUseCaseTest {
    @InjectMocks
    private SaveProgramaAcademicoUseCase saveProgramaAcademicoUseCase;

    @Mock
    private ProgramaAcademicoRepository programaAcademicoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a valid programa academico, when saving the programa, then it should be saved successfully")
    void testSaveProgramaAcademico_Success() {
        // Arrange
        Long programaId = 1L;
        ProgramaAcademico programaAcademico = ProgramaAcademicoMocks.getPrograma(programaId);
        when(programaAcademicoRepository.getProgramaById(programaId)).thenReturn(programaAcademico);

        // Act & Assert
        assertDoesNotThrow(() -> saveProgramaAcademicoUseCase.action(programaAcademico));
    }

    @Test
    @DisplayName("Given an invalid programa academico, when saving the programa, then it should throw an exception")
    void testSaveProgramaAcademico_TechnicalError() {
        // Arrange
        Long programaId = 1L;

        // Act & Assert
        doThrow(AcademiaExceptions.class).when(programaAcademicoRepository).saveProgramaAcademico(ProgramaAcademicoMocks.getPrograma(programaId));
        assertThrows(AcademiaExceptions.class, () -> saveProgramaAcademicoUseCase
                .action(ProgramaAcademicoMocks.getPrograma(programaId)));
    }
}