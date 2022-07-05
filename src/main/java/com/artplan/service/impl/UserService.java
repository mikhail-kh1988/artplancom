package com.artplan.service.impl;

import com.artplan.dto.MessageDto;
import com.artplan.dto.NewUserDto;
import com.artplan.entity.Animal;
import com.artplan.entity.User;
import com.artplan.exception.NoUserException;
import com.artplan.exception.NotUniqueLoginException;
import com.artplan.repository.AnimalRepository;
import com.artplan.repository.UserRepository;
import com.artplan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public List<Animal> getMyAnimal(long userID) throws NoUserException {
        Optional<User> getUsers = repository.findById(userID);
        if (getUsers.isEmpty())
            throw  new NoUserException("");
        return getUsers.get().getMyAnimals();
    }

    @Override
    public void createNewUser(NewUserDto dto) throws NotUniqueLoginException {
        if(validateUserLogin(dto.getLogin()))
            throw new NotUniqueLoginException("ТАКОЙ ПОЛЬЗОВАТЕЛЬ УЖЕ ЗАРЕГИСТРИРОВАН!");
        User user = new User();
        user.setAttempts(0);
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        repository.save(user);
    }

    @Override
    public boolean validateUserLogin(String login) {
        if (repository.findByLogin(login) != null)
            return true;
        return false;
    }

    @Override
    public MessageDto serviceValidateUserLogin(String login){
        if (validateUserLogin(login))
            return new MessageDto("Пользователь с таким логином уже загенистрирован!");
        return new MessageDto("Логин свободен!");
    }

    @Override
    public User findUserByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Integer getCountAttempts(String login) {
        User user = repository.findByLogin(login);
        return user.getAttempts();
    }

    @Override
    public void updateCountAttempts(String login) {
        User user = repository.findByLogin(login);
        if (user.getAttempts() == 10){
            user.setBlocked(true);
        }
        user.setAttempts(user.getAttempts()+1);
        repository.save(user);
    }

    @Override
    public void trueLogin(String login) {
        User user = repository.findByLogin(login);
        user.setAttempts(0);
        repository.save(user);
    }

    @Override
    public void makeAnimalYours(Long ID, String animalName) {
        User user = repository.findById(ID).get();
        Animal animal = animalRepository.findByName(animalName);

        List<Animal> animals = new ArrayList<>();
        animals.add(animal);

        user.setMyAnimals(animals);
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByLogin(username);
    }
}
