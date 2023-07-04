package com.microservice.academia.exceptionhandler;

import com.microservice.academia.domain.exeptions.UserNotFoundException;
import com.microservice.academia.exceptionhandler.response.ErrorDetails;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions.AcademicProgramNotFoundException;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions.InvalidAssignmentException;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions.PensumNotFoundException;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.exeptions.ProgramDeletionNotAllowedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        String message = String.format("%s, %s", exception.getMessage(), "the user haven't the permission necessary that realize this action");

        ErrorDetails res = new ErrorDetails(LocalDate.now(), message, request.getDescription(false));

        return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCourseUniqueGroupSemesterException(UserNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AcademicProgramNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCourseUniqueGroupSemesterException(AcademicProgramNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProgramDeletionNotAllowedException.class)
    public ResponseEntity<ErrorDetails> handleCourseUniqueGroupSemesterException(ProgramDeletionNotAllowedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAssignmentException.class)
    public ResponseEntity<ErrorDetails> handleCourseUniqueGroupSemesterException(InvalidAssignmentException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PensumNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCourseUniqueGroupSemesterException(PensumNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).collect(Collectors.joining(", "));
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), message, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}

