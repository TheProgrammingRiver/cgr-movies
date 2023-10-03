package com.example.cgrmovies.service;

import com.example.cgrmovies.exception.InformationExistsException;
import com.example.cgrmovies.exception.InformationNotFoundException;
import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.model.Movie;
import com.example.cgrmovies.repository.GenreRepository;
import com.example.cgrmovies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MovieService {
    Logger log = Logger.getLogger(MovieService.class.getName());
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final GenreService genreService;
    @Autowired
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, GenreService genreService) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.genreService = genreService;
    }
    /**
     * Creates a genre movie.
     *
     * @param  genreId   the ID of the genre
     * @param  movie     the movie to be created
     * @return           the created movie
     */
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

    public List<Movie> getAllGenreMovies(Long genreId){
        Genre genre = genreService.getGenreById(genreId);
        if (genre != null) {
            List<Movie> movieList = movieRepository.findAllByGenreId(genreId);
            if (movieList.isEmpty()) {
                throw new InformationNotFoundException("Movie list is empty for genre with id " + genreId);
            } else {
                return movieList;
            }
        }
        return null;
    }

    public Movie getMovieByIdAndGenreId(Long genreId, Long movieId){
        Genre genre = genreService.getGenreById(genreId);
        if (genre != null) {
            Optional<Movie> movie = movieRepository.findByIdAndGenreId(movieId, genreId);
            if (movie.isEmpty()) {
                throw new InformationNotFoundException("Movie with id " + movieId + " and genre with id " + genreId + " not found");
            } else {
                return movie.get();
            }
        }
        return null;
    }

    public List<Movie> getAllMovies(){
        List<Long> genreIdList = genreRepository.findIdsByUserId(genreService.getCurrentLoggedInUser().getId());

        if (!genreIdList.isEmpty()) {
            log.info("HERE: all genre ids not empty");
            List<Movie> movieList = movieRepository.findAllByGenreIdIn(genreIdList);
            if (movieList.isEmpty()) {
                throw new InformationNotFoundException("Movie list is empty");
            } else {
                log.info("HERE: all movies returned empty");
                return movieList;
            }
        }
        return null;
    }

    public Movie updateGenreMovie( Long genreId, Long movieId, Movie movie){
        Genre genre = genreService.getGenreById(genreId);
        if (genre != null){
            Optional<Movie> existingMovie = movieRepository.findByIdAndGenreId(movieId, genreId);
            if (existingMovie.isPresent()){
                existingMovie.get().setName(movie.getName());
                existingMovie.get().setDescription(movie.getDescription());
                existingMovie.get().setRating(movie.getRating());
                return movieRepository.save(existingMovie.get());
            } else {
                throw new InformationNotFoundException("Movie " + movie.getName() + " not found");
            }
        }
        return null;
    }

    public Movie deleteGenreMovie(Long genreId, Long movieId){
        Genre genre = genreService.getGenreById(genreId);
        if (genre != null){
            Optional<Movie> existingMovie = movieRepository.findByIdAndGenreId(movieId, genreId);
            if (existingMovie.isPresent()){
                movieRepository.deleteById(movieId);
                return existingMovie.get();
            } else {
                throw new InformationNotFoundException("Movie " + movieId + " not found");
            }
        }
        return null;
    }
}
