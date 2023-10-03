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

        //genres
        Genre genre1 = new Genre();
        genre1.setName("Action");
        genre1.setDescription("Action description");
        genre1.setUser(user);
        genreRepository.save(genre1);

        Genre genre2 = new Genre();
        genre2.setName("Comedy");
        genre2.setDescription("Comedy description");
        genre2.setUser(user);
        genreRepository.save(genre2);

        Genre genre3 = new Genre();
        genre3.setName("Horror");
        genre3.setDescription("Horror description");
        genre3.setUser(user);
        genreRepository.save(genre3);

        Genre genre4 = new Genre();
        genre4.setName("Sci-fi");
        genre4.setDescription("Sci-Fi description");
        genre4.setUser(user);
        genreRepository.save(genre4);

        //genre1 movies
        Movie movie1 = new Movie();
        movie1.setName("Movie 1");
        movie1.setDescription("Movie 1 Description");
        movie1.setGenre(genre1);
        movie1.setRating(2);
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setName("Movie 2");
        movie2.setDescription("Movie 2 Description");
        movie2.setGenre(genre1);
        movie2.setRating(1);
        movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setName("Movie 3");
        movie3.setDescription("Movie 3 Description");
        movie3.setGenre(genre1);
        movie3.setRating(3);
        movieRepository.save(movie3);


        //genre2 movies
        Movie movie4 = new Movie();
        movie4.setName("Movie 4");
        movie4.setDescription("Movie 4 Description");
        movie4.setGenre(genre2);
        movie4.setRating(2);
        movieRepository.save(movie4);

        Movie movie5 = new Movie();
        movie5.setName("Movie 3");
        movie5.setDescription("Movie 5 Description");
        movie5.setGenre(genre2);
        movie5.setRating(1);
        movieRepository.save(movie5);

        //genre3 movies
        Movie movie6 = new Movie();
        movie6.setName("Movie 6");
        movie6.setDescription("Movie 6 Description");
        movie6.setGenre(genre2);
        movie6.setRating(3);
        movieRepository.save(movie6);


    }

}
