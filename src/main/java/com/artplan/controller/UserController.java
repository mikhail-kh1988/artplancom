package com.artplan.controller;

import com.artplan.dto.MessageDto;
import com.artplan.dto.NewUserDto;
import com.artplan.entity.Animal;
import com.artplan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping("/register")
    public ResponseEntity<MessageDto> registerNewUser(@RequestBody @Validated NewUserDto dto){
        service.createNewUser(dto);
        return ResponseEntity.ok(new MessageDto("Пользователь зарегистрован!"));
    }

    @GetMapping("/validate/{login}")
    public ResponseEntity<MessageDto> validateLogin(@PathVariable String login){
        return ResponseEntity.ok(service.serviceValidateUserLogin(login));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Animal>> getMyAnimals(@PathVariable Long id){
        return ResponseEntity.ok(service.getMyAnimal(id));
    }

    @GetMapping("/{id}/{animalName}")
    public ResponseEntity<MessageDto> addMyAnimal(@PathVariable Long id, @PathVariable String animalName){
        service.makeAnimalYours(id, animalName);
        return ResponseEntity.ok(new MessageDto("Животное добавлено в список моих животных"));
    }
}
