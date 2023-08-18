package com.example.rentinfo.controller.advice;

import com.example.rentinfo.exception.InformationDataException;
import com.example.rentinfo.model.ExceptionDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InformationDataException.class)
    public ResponseEntity<ExceptionDetails> exceptionAdsNotFoundHandler() {
        ExceptionDetails notFoundDetails = new ExceptionDetails();
        notFoundDetails.setMessage("Information data not found");
        return ResponseEntity
                .badRequest()
                .body(notFoundDetails);
    }
}

