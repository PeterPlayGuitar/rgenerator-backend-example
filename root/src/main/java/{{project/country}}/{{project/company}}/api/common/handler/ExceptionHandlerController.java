package ru.peter.api.common.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.peter.api.common.response.ErrorResponse;
import ru.peter.domain.common.exception.BadRequestException;
import ru.peter.domain.common.exception.NotFoundException;
import ru.peter.security.exception.AuthTokenException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    private ResponseEntity<Object> badRequestException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), ex.getMessage(), 400);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = NotFoundException.class)
    private ResponseEntity<Object> notFoundException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), ex.getMessage(), 404);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = AuthTokenException.class)
    private ResponseEntity<Object> AuthTokenException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), ex.getMessage(), 400);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    private ResponseEntity<Object> authException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(ex.getClass().getSimpleName(), ex.getMessage(), 400);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
