package com.example.cgrmovies.controller;

import com.example.cgrmovies.model.Movie;
import com.example.cgrmovies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class MovieController {
    private MovieService movieService;
    static HashMap<String, Object> message = new HashMap<>();

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(path = "/genres/{genreId}/movies/")
    public ResponseEntity<?> createGenreMovie(@PathVariable(value = "genreId") Long genreId, @RequestBody Movie movie){
        Movie newMovie = movieService.createGenreMovie(genreId, movie);
        if (newMovie != null){
            message.put("message", "Movie successfully created");
            message.put("data", newMovie);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("message", "Movie cannot be created");
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

    }
}
