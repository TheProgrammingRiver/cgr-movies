package com.example.cgrmovies.service;

import com.example.cgrmovies.exception.InformationExistsException;
import com.example.cgrmovies.exception.InformationNotFoundException;
import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.model.Movie;
import com.example.cgrmovies.repository.GenreRepository;
import com.example.cgrmovies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MovieService {
    Logger log = Logger.getLogger(MovieService.class.getName());
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;
    private GenreService genreService = new GenreService();
    @Autowired
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }
    public Movie createGenreMovie(Long genreId, Movie movie){
        Optional<Genre> genre = genreRepository.findByIdAndUserId(genreId, genreService.getCurrentLoggedInUser().getId());
        log.info("Calling: create movie from Service, userId: " + genreService.getCurrentLoggedInUser().getId().toString());

        if (genre.isPresent()){
            Optional<Movie> existingMovie = movieRepository.findByNameAndGenreId(movie.getName(), genre.get().getId());
            if (existingMovie.isEmpty()){
                movie.setGenre(genre.get());
                return movieRepository.save(movie);
            } else {
                throw new InformationExistsException("Movie " + movie.getName() + " already exists");
            }
        } else {
            throw new InformationNotFoundException("Genre with id " + genreId + " not found");
        }
    }
    // RUD

}
