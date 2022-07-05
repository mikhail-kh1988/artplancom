package com.artplan.controller;

import com.artplan.dto.MessageDto;
import com.artplan.dto.NewAnimalDto;
import com.artplan.entity.Animal;
import com.artplan.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private IAnimalService service;

    @GetMapping("/")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        return ResponseEntity.ok(service.getAllAnimals());
    }

    @PutMapping("/add")
    public ResponseEntity<MessageDto> addNewAnimal(@RequestBody @Validated NewAnimalDto dto){
        service.createNewAnimal(dto);
        return ResponseEntity.ok(new MessageDto("Новое животное зарегистриовано!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable long id){
        return ResponseEntity.ok(service.getAnimalById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteAnimal(@PathVariable long id){
        service.deleteAnimalById(id);
        return ResponseEntity.ok(new MessageDto("Животное удалено"));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<MessageDto> updateAnimal(@RequestBody @Validated NewAnimalDto dto, @PathVariable long id){
        service.updateAnimal(id, dto);
        return ResponseEntity.ok(new MessageDto("Запись с ID "+id+" обновлена."));
    }

}
