package com.cliente.escola.gradecurricular.handler;

import com.cliente.escola.gradecurricular.exception.MateriaException;
import com.cliente.escola.gradecurricular.model.ErrorMapResponse;
import com.cliente.escola.gradecurricular.model.ErrorMapResponse.ErrorMapResponseBuilder;
import com.cliente.escola.gradecurricular.model.ErrorResponse;
import com.cliente.escola.gradecurricular.model.ErrorResponse.ErrorResponseBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {
    
    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException message) {
       ErrorResponseBuilder error = ErrorResponse.builder(); 
       error.httpStatus(message.getHttpStatus().value());
       error.message(message.getMessage());
       error.timestamp(System.currentTimeMillis());

       return ResponseEntity.status(message.getHttpStatus()).body(error.build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMapResponse> MethodArgumentNotValidException(MethodArgumentNotValidException message) {
        Map<String, String> error = new HashMap<>();
        message.getBindingResult().getAllErrors().forEach(err -> {
            String fields = ((FieldError) err).getField();
            String msg = err.getDefaultMessage();
            error.put(fields, msg);
        });

        ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();
        errorMap.error(error)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
    }
}
