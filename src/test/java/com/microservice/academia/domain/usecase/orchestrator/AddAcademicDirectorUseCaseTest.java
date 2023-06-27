package com.microservice.academia.domain.usecase.orchestrator;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.User.UserModel;
import com.microservice.academia.domain.model.ports.spi.AddAcademicDirectorPersistencePort;
import com.microservice.academia.domain.usecase.GetUserModelUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AddAcademicDirectorUseCaseTest {
    @Mock
    private AddAcademicDirectorPersistencePort addAcademicDirectorPersistencePort;

    @Mock
    private GetUserModelUseCase getUserModelUseCase;

    @InjectMocks
    private AddAcademicDirectorUseCase addAcademicDirectorUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addAcademicDirectorUseCase = new AddAcademicDirectorUseCase(addAcademicDirectorPersistencePort, getUserModelUseCase);
    }

    @Test
    @DisplayName("Given valid academic program ID and user ID, when adding academic director, " +
            "then it should invoke the persistence port")
    void addAcademicDirector_ShouldAddDirector() {
        // Arrange
        Long academicProgramId = 1L;
        Long userId = 2L;
        UserModel userModel = UserModel.builder()
                .id(userId)
                .build();

        when(getUserModelUseCase.action(anyLong())).thenReturn(userModel);

        // Act
        addAcademicDirectorUseCase.addAcademicDirector(academicProgramId, userId);

        // Assert
        verify(getUserModelUseCase).action(userId);
        verify(addAcademicDirectorPersistencePort).addAcademicDirector(academicProgramId, userId);
    }

    @Test
    @DisplayName("Given valid academic program ID and user ID, when user ID is null in the user model, " +
            "then it should throw an exception with the correct message and status")
    void addAcademicDirector_NullUserModelId_ThrowsException() {
        // Arrange
        Long academicProgramId = 1L;
        Long userId = 2L;
        UserModel userModel = UserModel.builder().id(null).build();

        when(getUserModelUseCase.action((userId))).thenReturn(userModel);

        // Act
        AcademiaExceptions exception = assertThrows(AcademiaExceptions.class,
                () -> addAcademicDirectorUseCase.addAcademicDirector(academicProgramId, userId));

        //Assert
        assertEquals("userId cannot be null", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
}