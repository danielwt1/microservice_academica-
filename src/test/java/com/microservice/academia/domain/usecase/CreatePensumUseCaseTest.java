package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.pensun.Pensum;
import com.microservice.academia.domain.model.ports.spi.CreatePensumPersistencePort;
import com.microservice.academia.mock.programaAcademico.PensumMockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreatePensumUseCaseTest {

    @InjectMocks
    private CreatePensumUseCase createPensumUseCase;

    @Mock
    private CreatePensumPersistencePort createPensumPersistencePort;

    @BeforeEach
    void setUp() {
        createPensumUseCase = new CreatePensumUseCase(createPensumPersistencePort);
    }

    @Test
    @DisplayName("Given a valid pensum, when saving the pensum, then it should be saved successfully")
    void testCreatePensumSuccess() {
        // Arrange
        Long pensumId = 1L;
        Pensum pensum = PensumMockData.getPensum(pensumId);
        when(createPensumPersistencePort.createPensum(pensum)).thenReturn(pensum);

        // Act
        Pensum result = createPensumUseCase.createPensum(pensum);

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
        doThrow(AcademiaExceptions.class).when(createPensumPersistencePort).createPensum(pensum);
        assertThrows(AcademiaExceptions.class, () -> createPensumUseCase.createPensum(pensum));
    }
}