package com.artplan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Такой пользователь уже зарегистрирован!")
public class NotUniqueLoginException extends RuntimeException{
    public NotUniqueLoginException(String message){
        super(message);
    }
}
