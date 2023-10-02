package com.example.cgrmovies.seed;

import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.model.Movie;
import com.example.cgrmovies.model.User;
import com.example.cgrmovies.repository.GenreRepository;
import com.example.cgrmovies.repository.MovieRepository;
import com.example.cgrmovies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final GenreRepository genreRepository;

    private final MovieRepository movieRepository;

    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository,
                    GenreRepository genreRepository, MovieRepository movieRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmailAddress("example@email.com");
        user.setPassword(passwordEncoder.encode("password"));
        userRepository.save(user);

        Genre genre = new Genre();
        genre.setName("Action");
        genre.setDescription("Description");
        genreRepository.save(genre);

        Movie movie = new Movie();
        movie.setName("Movie 1");
        movie.setDescription("Movie 1 Description");
        movie.setGenre(genre);
        movie.setRating(3);
        movieRepository.save(movie);
    }

}
