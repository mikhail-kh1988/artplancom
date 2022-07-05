package com.artplan.controller;

import com.artplan.exception.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotUniqueLoginException.class)
    protected ResponseEntity<SomeException> handleNotUniqueLoginException(){
        return new ResponseEntity<>(new SomeException(001, "Пользователь уже зарегистрирован!"), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(NotUniqueNameAnimalException.class)
    protected ResponseEntity<SomeException> handleNotUniqueNameAnimalException(){
        return new ResponseEntity<>(new SomeException(002, "Животное с такой кличкой уже зарегистрировано!"), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(NotExistsTypeAnimalException.class)
    protected ResponseEntity<SomeException> handleNotUniqueTypeAnimalException(){
        return new ResponseEntity<>(new SomeException(003, "Нет такого типа животного!"), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<SomeException> handleBadCredentialsException(){
        return new ResponseEntity<>(new SomeException(004, "Не верный пароль!"), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(BlockedUserException.class)
    protected ResponseEntity<SomeException> handleBlockedUserException(){
        return new ResponseEntity<>(new SomeException(005, "Пользователь заблокирован!"), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(NoUserException.class)
    protected ResponseEntity<SomeException> handleNoUserException(){
        return new ResponseEntity<>(new SomeException(006, "Нет такого пользователя!"), HttpStatus.ACCEPTED);
    }

    @Data
    @AllArgsConstructor
    private static class SomeException{
        private int code;
        private String error;
    }
}
