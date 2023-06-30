package com.microservice.academia.domain.usecase;

import com.microservice.academia.domain.model.model.pensun.Assignment;
import com.microservice.academia.domain.model.ports.spi.AddAssignmentPersistencePort;
import com.microservice.academia.mock.programaAcademico.AssignmentMockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@SpringBootTest
class AddAssignmentUseCaseTest {
    @Mock
    private AddAssignmentPersistencePort addAssignmentPersistencePort;

    @InjectMocks
    private AddAssignmentUseCase addAssignmentUseCase;

    @BeforeEach
    void setUp() {
        addAssignmentUseCase = new AddAssignmentUseCase(addAssignmentPersistencePort);
    }

    @Test
    void addAssignmentServicePort() {
        // Arrange
        Long assignmentId = 1L;
        Assignment assignment = AssignmentMockData.getAssignment(assignmentId);
        // Act
        addAssignmentUseCase.addAssignmentServicePort(assignment);

        // Assert
        verify(addAssignmentPersistencePort).addAssignmentServicePort(assignment);
    }

    @Test
    void testAddAssignmentServicePort_NullPersistencePort() {
        // Arrange
        Long assignmentId = 1L;
        Assignment assignment = AssignmentMockData.getAssignment(assignmentId);
        addAssignmentUseCase = new AddAssignmentUseCase(null);

        // Act y Assert
        assertThrows(NullPointerException.class,
                () -> addAssignmentUseCase.addAssignmentServicePort(assignment));
        verifyNoInteractions(addAssignmentPersistencePort);
    }

}