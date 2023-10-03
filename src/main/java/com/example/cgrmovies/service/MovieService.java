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

    /**
     * Retrieves all movies of a specific genre.
     *
     * @param  genreId   the ID of the genre
     * @return           a list of movies belonging to the genre
     */
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

    /**
     * Retrieves a movie by its genre ID and movie ID.
     *
     * @param  genreId   the ID of the genre
     * @param  movieId   the ID of the movie
     * @return           the movie with the specified genre ID and movie ID, or null if not found
     */
    public Movie getMovieByIdAndGenreId(Long movieId, Long genreId){
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

    /**
     * Retrieves all movies based on the user's genre preferences.
     *
     * @return  A list of movies that match the user's genre preferences.
     */
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

    public List<Movie> getMoviesByStatus(Movie.MovieStatus status){
        List<Movie> movieList = movieRepository.findAllByStatus(status);
        if (movieList.isEmpty()) {
            throw new InformationNotFoundException("Movie list is empty for status " + status.name());
        } else {
            return movieList;
        }
    }
    /**
     * Updates the genre of a movie.
     *
     * @param  genreId  the ID of the genre to update
     * @param  movieId  the ID of the movie to update
     * @param  movie    the updated movie object
     * @return          the updated movie object
     */
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

    /**
     * Deletes a movie from a genre.
     *
     * @param  genreId   the ID of the genre
     * @param  movieId   the ID of the movie
     * @return           the deleted movie
     */
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
