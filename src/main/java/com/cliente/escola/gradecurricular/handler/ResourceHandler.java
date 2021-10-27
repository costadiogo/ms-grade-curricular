package com.cliente.escola.gradecurricular.handler;

import com.cliente.escola.gradecurricular.exception.MateriaException;
import com.cliente.escola.gradecurricular.model.Response;
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
    public ResponseEntity<Response<String>> handlerMateriaException(MateriaException message) {
       Response<String> response = new Response<>();
        response.setStatusCode(message.getHttpStatus().value());
        response.setData(message.getMessage());

       return ResponseEntity.status(message.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> MethodArgumentNotValidException(
            MethodArgumentNotValidException message) {

        Map<String, String> error = new HashMap<>();
        message.getBindingResult().getAllErrors().forEach(err -> {
            String fields = ((FieldError) err).getField();
            String msg = err.getDefaultMessage();
            error.put(fields, msg);
        });

        Response<Map<String, String>> response = new Response<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setData(error);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
