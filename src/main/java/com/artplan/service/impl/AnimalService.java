package com.artplan.service.impl;

import com.artplan.dto.NewAnimalDto;
import com.artplan.entity.Animal;
import com.artplan.entity.TypeAnimal;
import com.artplan.exception.NotUniqueNameAnimalException;
import com.artplan.exception.NotExistsTypeAnimalException;
import com.artplan.repository.AnimalRepository;
import com.artplan.repository.TypeAnimalRepository;
import com.artplan.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService implements IAnimalService {

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private TypeAnimalRepository typeRepository;

    @Override
    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        for (Animal a: repository.findAll()) {
            animals.add(a);
        }
        return animals;
    }

    @Override
    public void createNewAnimal(NewAnimalDto dto) throws NotUniqueNameAnimalException {
        if (validateNameAnimal(dto.getName()))
            throw new NotUniqueNameAnimalException("Животное с таким именем уже есть!");
        if (validateTypeAnimal(dto.getTypeAnimal()))
            throw new NotExistsTypeAnimalException("Нет такого типа животного!");
        Animal animal = new Animal();
        TypeAnimal type = typeRepository.findByType(dto.getTypeAnimal());

        animal.setBirthDay(dto.getBirthDay());
        animal.setName(dto.getName());
        animal.setSex(dto.getSex());
        type.setType(dto.getTypeAnimal());
        animal.setType(type);

        repository.save(animal);
    }

    @Override
    public Animal getAnimalById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteAnimalById(long id) {
        Animal animal = repository.findById(id).get();
        repository.delete(animal);
    }

    @Override
    public void updateAnimal(long id, NewAnimalDto dto) throws NotUniqueNameAnimalException{
        if (validateNameAnimal(dto.getName()))
            throw new NotUniqueNameAnimalException("Животное с таким именем уже есть!");

        Animal animal = repository.findById(id).get();

        animal.setSex(dto.getSex());
        animal.setName(dto.getName());
        animal.setBirthDay(dto.getBirthDay());

        repository.save(animal);
    }

    private boolean validateNameAnimal(String name){
        if (repository.findByName(name) != null)
            return true;
        return false;
    }

    private boolean validateTypeAnimal(String type){
        if (typeRepository.findByType(type) != null)
            return false;
        return true;
    }
}
