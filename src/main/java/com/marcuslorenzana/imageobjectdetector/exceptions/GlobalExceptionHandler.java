package com.marcuslorenzana.imageobjectdetector.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marcuslorenzana.imageobjectdetector.constants.MessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A global exception handler to gracefully catch errors and return useful responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleJsonProcessingException(JsonProcessingException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", MessageConstants.MALFORMED_JSON_REQUEST_ERROR);
        response.put("message", ex.getOriginalMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleIOException(IOException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", MessageConstants.IMAGE_PROCESSING_EXCEPTION_ERROR);
        response.put("message", MessageConstants.IMAGE_PROCESSING_EXCEPTION_MSG);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(InternalError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleInternalServerError(InternalError e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", MessageConstants.INTERNAL_SERVER_ERROR);
        response.put("message", MessageConstants.INTERNAL_SERVER_ERROR_MSG);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}