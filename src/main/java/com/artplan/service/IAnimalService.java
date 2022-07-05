package com.artplan.service;

import com.artplan.dto.NewAnimalDto;
import com.artplan.entity.Animal;

import java.util.List;

public interface IAnimalService {

    List<Animal> getAllAnimals();
    void createNewAnimal(NewAnimalDto dto);
    Animal getAnimalById(long id);
    void deleteAnimalById(long id);
    void updateAnimal(long id, NewAnimalDto dto);

}
