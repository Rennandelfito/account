package org.banking.controller.handler;

import org.banking.entities.excption.AccountValidationException;
import org.banking.entities.excption.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler (AccountValidationException.class)
    ResponseEntity<ApiError> validationException(AccountValidationException ex){
        return ResponseEntity.badRequest().body(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
