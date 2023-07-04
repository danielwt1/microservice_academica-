package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.model.ports.repositories.PensumPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletePensumTest {
    @Mock
    private PensumPersistencePort pensumPersistencePort;

    @InjectMocks
    private DeletePensum deletePensum;

    @BeforeEach
    void setUp() {
        deletePensum = new DeletePensum(pensumPersistencePort);
    }

    @Test
    @DisplayName("Given an academy program ID, when deleting the academy program, " +
            "then it should invoke the delete persistence port")
    void testDeletePensumSusses() {
        // Arrange
        Long idPensum = 1L;

        // Act
        deletePensum.action(idPensum);

        // Assert
        verify(pensumPersistencePort).deletePensum(idPensum);
    }
}