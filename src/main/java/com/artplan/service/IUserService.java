package com.artplan.service;

import com.artplan.dto.MessageDto;
import com.artplan.dto.NewUserDto;
import com.artplan.entity.Animal;
import com.artplan.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<Animal> getMyAnimal(long userID);
    void createNewUser(NewUserDto dto);
    boolean validateUserLogin(String login);
    MessageDto serviceValidateUserLogin(String login);
    User findUserByLogin(String login);
    Integer getCountAttempts(String login);
    void updateCountAttempts(String login);
    void trueLogin(String login);
    void makeAnimalYours(Long ID, String animalName);

}
