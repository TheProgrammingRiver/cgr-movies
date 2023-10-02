package com.example.cgrmovies.controller;

import com.example.cgrmovies.model.Movie;
import com.example.cgrmovies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    @GetMapping(path = "/genres/{genreId}/movies/")
    public ResponseEntity<?> getAllGenreMovies(@PathVariable(value = "genreId") Long genreId){
        List<Movie> movieList = movieService.getAllGenreMovies(genreId);
        if (movieList != null){
            message.put("message", "Movie list of genre with "+ genreId + " retrieved");
            message.put("data", movieList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Movie list of genre with " + genreId + " cannot be retrieved");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/genres/{genreId}/movies/{movieId}")
    public ResponseEntity<?> updateGenreMovie(@PathVariable(value = "genreId") Long genreId, @PathVariable(value = "movieId")
    Long movieId, @RequestBody Movie movie){
        Movie updatedMovie = movieService.updateGenreMovie(genreId, movieId, movie);
        if (updatedMovie != null){
            message.put("message", "Movie with genre " + genreId + " updated");
            message.put("data", updatedMovie);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Movie with genre " + genreId + " cannot be updated");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
