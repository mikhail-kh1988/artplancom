package com.artplan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ACCEPTED, reason ="")
public class NotExistsTypeAnimalException extends RuntimeException{
    public NotExistsTypeAnimalException(String message){
        super(message);
    }

}
