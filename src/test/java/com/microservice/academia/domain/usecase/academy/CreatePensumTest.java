package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.domain.model.ports.repositories.PensumPersistencePort;
import com.microservice.academia.mock.programaAcademico.PensumMockData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePensumTest {
    @Mock
    private PensumPersistencePort pensumPersistencePort;

    @InjectMocks
    private CreatePensum createPensum;

    @AfterEach
    void tearDown() {
        createPensum = new CreatePensum(pensumPersistencePort);
    }

    @Test
    @DisplayName("Given a valid pensum, when saving the pensum, then it should be saved successfully")
    void testCreatePensumSuccess() {
        // Arrange
        Long pensumId = 1L;
        Pensum pensum = PensumMockData.getPensum(pensumId);
        when(pensumPersistencePort.createPensum(pensum)).thenReturn(pensum);

        // Act
        Pensum result = createPensum.action(pensum);

        // Assert
        assertEquals(pensum, result);
    }

    @Test
    @DisplayName("Given an invalid pensum, when saving the pensum, then it should throw an exception")
    void testCreateProgramaAcademicoTechnicalError() {
        // Arrange
        Long pensumId = 1L;
        Pensum pensum = PensumMockData.getPensum(pensumId);

        //Act & Assert
        doThrow(AcademiaExceptions.class).when(pensumPersistencePort).createPensum(pensum);
        assertThrows(AcademiaExceptions.class, () -> createPensum.action(pensum));
    }
}