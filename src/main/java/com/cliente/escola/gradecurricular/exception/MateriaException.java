package com.cliente.escola.gradecurricular.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class MateriaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public MateriaException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    
}
