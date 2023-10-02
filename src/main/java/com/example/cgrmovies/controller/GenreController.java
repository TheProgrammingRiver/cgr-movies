package com.example.cgrmovies.controller;

import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api")
public class GenreController {

    private GenreService genreService;

    static HashMap<String, Object> message = new HashMap<>();
    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping(path = "/genres/")
    public ResponseEntity<?> createGenre(@RequestBody Genre genre) {
        Genre newGenre = genreService.createGenre(genre);
        if (newGenre != null) {
            message.put("message", "Genre created successfully");
            message.put("data", newGenre);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Cannot create genre");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
