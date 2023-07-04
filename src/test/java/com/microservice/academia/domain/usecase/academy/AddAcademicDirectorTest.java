package com.microservice.academia.domain.usecase.academy;

import com.microservice.academia.domain.exeptions.AcademiaExceptions;
import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.domain.model.model.userservice.UserModel;
import com.microservice.academia.domain.model.ports.repositories.AcademyPersistencePort;
import com.microservice.academia.domain.usecase.userapi.GetUserModelUseCase;
import com.microservice.academia.mock.programaAcademico.AcademicProgramMockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddAcademicDirectorTest {
    @InjectMocks
    private AddAcademicDirector addAcademicDirector;

    @Mock
    private AcademyPersistencePort academyPersistencePort;

    @Mock
    private GetUserModelUseCase getUserModelUseCase;

    @BeforeEach
    public void setUp() {
        addAcademicDirector = new AddAcademicDirector(academyPersistencePort, getUserModelUseCase);
    }

    @Test
    @DisplayName("Given valid academy program ID and user ID, when adding academy director, " +
            "then it should invoke the persistence port")
    void TestValidUserAssignDirector() {
        // Arrange
        Long programId = 1L;
        Long userId = 1L;
        Long userModelId = 2L;
        AcademicProgram academicProgram = AcademicProgramMockData.getProgram(programId);

        UserModel userModel = UserModel.builder()
                .id(userId)
                .typeUserId(userModelId)
                .build();

        when(getUserModelUseCase.action(userId)).thenReturn(userModel);

        // Act
        addAcademicDirector.action(programId, userId);


        verify(getUserModelUseCase, times(1)).action(userId);
        verify(academyPersistencePort, times(1)).assignAcademicDirector(programId, userId);
    }

    @Test
    @DisplayName("Given valid academy program ID and user ID, when user ID is null in the user model, " +
            "then it should throw an exception with the correct message and status")
    void addAcademicDirector_NullUserModelId_ThrowsException() {
        // Arrange
        Long academicProgramId = 1L;
        Long userId = 2L;
        UserModel userModel = UserModel.builder().id(null).build();

        when(getUserModelUseCase.action((userId))).thenReturn(userModel);

        // Act
        AcademiaExceptions exception = assertThrows(AcademiaExceptions.class,
                () -> addAcademicDirector.action(academicProgramId, userId));

        //Assert
        assertEquals("Usuario no registrado", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
}