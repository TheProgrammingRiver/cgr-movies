package com.example.cgrmovies.controller;

import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.model.User;
import com.example.cgrmovies.model.request.LoginRequest;
import com.example.cgrmovies.model.response.LoginResponse;
import com.example.cgrmovies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {

    private HashMap<String, Object> message = new HashMap<>();

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles loginRequest
     * @param loginRequest
     * @return
     */
    @PostMapping(path = "/login/")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<String>jwtToken = userService.loginUser(loginRequest);
        if (jwtToken.isPresent()){
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body
                    (new LoginResponse("Authentication failed "));
        }
    }

    @PostMapping(path = "/register/")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User newUser = userService.register(user);
        if (newUser != null) {
            message.put("message", "User created successfully");
            message.put("data", newUser);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Cannot create user");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}















