package com.example.cgrmovies.service;

import com.example.cgrmovies.exception.InformationExistsException;
import com.example.cgrmovies.exception.InformationNotFoundException;
import com.example.cgrmovies.model.User;
import com.example.cgrmovies.model.request.LoginRequest;
import com.example.cgrmovies.security.JWTUtils;
import com.example.cgrmovies.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.cgrmovies.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    /**
     * Injects dependencies and enables userService to access the resources.
     * @param userRepository
     */
    @Autowired
    public UserService(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
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

    /**
     * Login a user with the provided login request.
     *
     * @param  loginRequest  the login request containing the user's email address and password
     * @return               an optional string representing the generated JWT token if login is successful,
     *                       or an empty optional if login fails
     */
    public Optional<String> loginUser(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    public User loginRegister(User user){
        if (!userRepository.existsByEmailAddress(user.getEmailAddress())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new InformationExistsException("user with email address " + user.getEmailAddress() + " already exist");
        }
    }
}
