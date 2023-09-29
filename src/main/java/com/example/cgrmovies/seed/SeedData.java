package com.example.cgrmovies.seed;

import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.model.User;
import com.example.cgrmovies.repository.GenreRepository;
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

    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository, GenreRepository genreRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
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

    }

}
