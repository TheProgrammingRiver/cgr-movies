package com.example.cgrmovies.service;

import com.example.cgrmovies.exception.InformationNotFoundException;
import com.example.cgrmovies.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cgrmovies.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Injects dependencies and enables userService to access the resources.
     * @param userRepository
     */
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Finds the user by the given email address
     * @param emailAddress
     * @return user
     */
    public User findByEmailAddress(String emailAddress){
        Optional<User> userOptional = userRepository.findUserByEmailAddress(emailAddress);
        if (userOptional.isPresent()){
            return userOptional.get();
        } else {
            throw new InformationNotFoundException("EmailAddress not found");
        }
    }
}
