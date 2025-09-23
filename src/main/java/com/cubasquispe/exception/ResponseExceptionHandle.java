package com.cubasquispe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice // Intercepta cualquier excepcion
public class ResponseExceptionHandle {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorRecord> handlerDefaultException(Exception ex, WebRequest request){
        CustomErrorRecord err = new CustomErrorRecord(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorRecord> handlerModelNotFoundException(ModelNotFoundException ex, WebRequest request){
        CustomErrorRecord err = new CustomErrorRecord(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
}