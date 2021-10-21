package com.cliente.escola.gradecurricular.handler;

import com.cliente.escola.gradecurricular.exception.MateriaException;
import com.cliente.escola.gradecurricular.model.ErrorResponse;
import com.cliente.escola.gradecurricular.model.ErrorResponse.ErrorResponseBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResouceHandler {
    
    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException message) {
       ErrorResponseBuilder error = ErrorResponse.builder(); 
       error.httpStatus(message.getHttpStatus().value());
       error.message(message.getMessage());
       error.timestamp(System.currentTimeMillis());

       return ResponseEntity.status(message.getHttpStatus()).body(error.build());
    }
}
