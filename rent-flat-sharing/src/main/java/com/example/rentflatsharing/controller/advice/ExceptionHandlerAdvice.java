package com.example.rentflatsharing.controller.advice;

import com.example.rentflatsharing.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ExceptionDetails>  HttpClientErrorExceptionHandler() {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(HttpStatus.UNAUTHORIZED.toString());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(exceptionDetails);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ExceptionDetails>  ServerErrorExceptionHandler() {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(HttpStatus.UNAUTHORIZED.toString());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(exceptionDetails);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDetails>  UserNotFoundExceptionHandler() {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(HttpStatus.NOT_FOUND.toString());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDetails);
    }
    @ExceptionHandler(ApartmentNotFoundException.class)
    public ResponseEntity<ExceptionDetails>  ApartmentNotFoundExceptionHandler() {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage("Apartment not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDetails);
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ExceptionDetails>  AddressNotFoundExceptionHandler() {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage("Address not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDetails);
    }
    @ExceptionHandler(ParseJsonToObjectException.class)
    public ResponseEntity<ExceptionDetails>  ParseJsonToObjectExceptionHandler() {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage("JSON processing exception");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionDetails);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails>  ValidationExceptionHandler() {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage("username or password entered incorrectly");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionDetails);
    }
}
