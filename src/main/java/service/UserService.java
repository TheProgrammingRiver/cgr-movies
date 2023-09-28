package service;

import exception.InformationNotFoundException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User findByEmailAddress(String emailAddress){
        Optional<User> userOptional = userRepository.findUserByEmailAddress(emailAddress);
        if (userOptional.isPresent()){
            return userOptional.get();
        } else {
            throw new InformationNotFoundException("EmailAddress not found");
        }
    }
}
