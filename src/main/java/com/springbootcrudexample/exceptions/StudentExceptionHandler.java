package com.springbootcrudexample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleStudentNotFoundException(StudentNotFoundException ex) {

        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), new Date(), "Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionDetails);
    }

    @ExceptionHandler(MissingAttributeException.class)
    public ResponseEntity<ExceptionDetails> handleMissingAttributeException(MissingAttributeException ex) {

        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), new Date(), "Bad Request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionDetails);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(BadRequestException ex) {

        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(), new Date(), "Bad Request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionDetails);
    }
}
