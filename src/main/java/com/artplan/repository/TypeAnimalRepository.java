package com.artplan.repository;

import com.artplan.entity.TypeAnimal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAnimalRepository extends CrudRepository<TypeAnimal, Long> {

    TypeAnimal findByType(String name);
}
