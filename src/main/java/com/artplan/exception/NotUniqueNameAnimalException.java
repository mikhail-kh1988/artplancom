package com.artplan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ACCEPTED, reason ="Животное с таким именем уже зарегистрировано!")
        public class NotUniqueNameAnimalException extends RuntimeException{
    public NotUniqueNameAnimalException(String message){
        super(message);
    }
}
