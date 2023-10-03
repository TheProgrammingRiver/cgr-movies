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
    private final MovieService movieService;
    static HashMap<String, Object> message = new HashMap<>();

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Create a new movie for a specific genre.
     *
     * @param  genreId  the ID of the genre
     * @param  movie    the movie object to be created
     * @return          a ResponseEntity object with the created movie and a success message if the movie is created successfully,
     *                  otherwise a ResponseEntity object with a failure message
     */
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

    /**
     * Retrieves all movies of a specific genre.
     *
     * @param  genreId  the ID of the genre
     * @return          a ResponseEntity object with a list of movies belonging to the genre
     *                   and a success message if the movies are retrieved successfully,
     *                   otherwise a ResponseEntity object with a failure message
     */
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

    /**
     * Retrieves a movie by its ID and genre ID.
     *
     * @param  genreId  the ID of the genre
     * @param  movieId  the ID of the movie
     * @return          a ResponseEntity containing the movie and a success message if the movie is found,
     *                  otherwise a ResponseEntity with a not found message
     */
    @GetMapping(path = "/genres/{genreId}/movies/{movieId}/")
    public ResponseEntity<?> getMovieByIdAndGenreId(@PathVariable(value = "genreId") Long genreId,
                                                    @PathVariable(value = "movieId") Long movieId){
        Movie movie = movieService.getMovieByIdAndGenreId(movieId, genreId);
        if (movie != null){
            message.put("message", "Movie with id " + movieId + " and genreId " + genreId + " retrieved");
            message.put("data", movie);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Movie with id " + movieId + " and genreId " + genreId + " cannot be retrieved");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = "/movies/byStatus/")
    public ResponseEntity<?> getMoviesByStatus(@RequestParam String status){
        List<Movie> movieList = movieService.getMoviesByStatus(Movie.MovieStatus.valueOf(status));
        if (movieList != null){
            message.put("message", "Movie list with " + status + " retrieved");
            message.put("data", movieList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Movie list with " + status + " cannot be retrieved");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path = "/movies/")
    public ResponseEntity<?> getAllMovies(){
        List<Movie> movieList = movieService.getAllMovies();
        if (movieList != null){
            message.put("message", "All Movies retrieved");
            message.put("data", movieList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Movie list cannot be retrieved");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping(path = "/genres/{genreId}/movies/{movieId}/")
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


    @DeleteMapping(path = "/genres/{genreId}/movies/{movieId}/")
    public ResponseEntity<?> deleteGenreMovie(@PathVariable(value = "genreId")Long genreId, @PathVariable(value = "movieId")Long movieId){
        Movie deletedMovie = movieService.deleteGenreMovie(genreId, movieId);
        if( deletedMovie != null){
            message.put("message", "Movie with genre " + genreId + " deleted");
            message.put("data", deletedMovie);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.put("message", "Movie with genre " + genreId + " cannot be deleted");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
