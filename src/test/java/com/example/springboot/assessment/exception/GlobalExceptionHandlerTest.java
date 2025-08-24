package com.example.springboot.assessment.exception;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.springboot.assessment.constant.AppConstant;
import com.example.springboot.assessment.dto.Response;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleResourceNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Book not found");

        ResponseEntity<Response<String>> response = exceptionHandler.handleResourceNotFound(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getStatus()).isEqualTo(AppConstant.ERROR);
        assertThat(response.getBody().getMessage()).isEqualTo("Book not found");
        assertThat(response.getBody().getData()).isNull();
    }

    @Test
    void testHandleValidationExceptions() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("error", "title", "Title is required");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<Response<java.util.Map<String, String>>> response
                = exceptionHandler.handleValidationExceptions(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getStatus()).isEqualTo(AppConstant.ERROR);
        assertThat(response.getBody().getMessage()).isEqualTo(AppConstant.VALIDATION_FAILED);
        assertThat(response.getBody().getData()).containsEntry("title", "Title is required");
    }

    @Test
    void testHandleGlobalException() {
        Exception ex = new Exception("Something went wrong");

        ResponseEntity<Response<String>> response = exceptionHandler.handleGlobalException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().getStatus()).isEqualTo(AppConstant.ERROR);
        assertThat(response.getBody().getMessage()).isEqualTo("Something went wrong");
        assertThat(response.getBody().getData()).isNull();
    }
}
