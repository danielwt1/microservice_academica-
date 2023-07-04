package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.model.model.academy.Assignment;
import com.microservice.academia.domain.model.ports.repositories.AssignmentPersistencePort;
import com.microservice.academia.mock.programaAcademico.AssignmentMockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class AddAssignmentTest {
    @Mock
    private AssignmentPersistencePort assignmentPersistencePort;

    @InjectMocks
    private AddAssignment addAssignment;

    @BeforeEach
    void setUp() {
        addAssignment = new AddAssignment(assignmentPersistencePort);
    }

    @Test
    void addAssignmentServicePort() {
        // Arrange
        Long assignmentId = 1L;
        Assignment assignment = AssignmentMockData.getAssignment(assignmentId);
        // Act
        addAssignment.action(assignment);

        // Assert
        verify(assignmentPersistencePort).addAssignment(assignment);
    }

    @Test
    void testAddAssignmentServicePort_NullPersistencePort() {
        // Arrange
        Long assignmentId = 1L;
        Assignment assignment = AssignmentMockData.getAssignment(assignmentId);
        addAssignment = new AddAssignment(null);

        // Act y Assert
        assertThrows(NullPointerException.class,
                () -> addAssignment.action(assignment));
        verifyNoInteractions(assignmentPersistencePort);
    }
}