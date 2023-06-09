package com.springbootcrudexample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingAttributeException extends RuntimeException {

    public MissingAttributeException(String msg) {
        super(msg);
    }

}
