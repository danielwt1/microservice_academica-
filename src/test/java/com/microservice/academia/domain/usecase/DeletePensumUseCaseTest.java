package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.ports.spi.DeletePensumPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
class DeletePensumUseCaseTest {
    @Mock
    private DeletePensumPersistencePort deletePensumPersistencePort;

    @InjectMocks
    private DeletePensumUseCase deletePensumUseCase;

    @BeforeEach
    void setUp() {
        deletePensumUseCase = new DeletePensumUseCase(deletePensumPersistencePort);
    }

    @Test
    @DisplayName("Given an academic program ID, when deleting the academic program, " +
            "then it should invoke the delete persistence port")
    void testDeletePensumSusses() {
        // Arrange
        Long idPensum = 1L;

        // Act
        deletePensumUseCase.deletePensum(idPensum);

        // Assert
        verify(deletePensumPersistencePort).deletePensum(idPensum);
    }
}