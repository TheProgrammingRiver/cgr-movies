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

        Genre genre5 = new Genre();
        genre5.setName("Romance");
        genre5.setDescription("Romance description");
        genre5.setUser(user);
        genreRepository.save(genre5);

        //genre1 movies
        Movie movie1 = new Movie();
        movie1.setName("Top Gun: Maverick");
        movie1.setDescription("After thirty years, Maverick is still pushing the envelope as a top naval aviator, but must confront ghosts of his past when he leads TOP GUN's elite graduates on a mission that demands the ultimate sacrifice from those chosen to fly it.");
        movie1.setGenre(genre1);
        movie1.setRating(2);
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setName("Fast Five");
        movie2.setDescription("Dominic Toretto and his crew of street racers plan a massive heist to buy their freedom while in the sights of a powerful Brazilian drug lord and a dangerous federal agent.");
        movie2.setGenre(genre1);
        movie2.setRating(1);
        movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setName("Avengers: Endgame");
        movie3.setDescription("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.");
        movie3.setGenre(genre1);
        movie3.setRating(3);
        movieRepository.save(movie3);


        //genre2 movies
        Movie movie4 = new Movie();
        movie4.setName("Shazam!");
        movie4.setDescription("A newly fostered young boy in search of his mother instead finds unexpected super powers and soon gains a powerful enemy.");
        movie4.setGenre(genre2);
        movie4.setRating(2);
        movieRepository.save(movie4);

        Movie movie5 = new Movie();
        movie5.setName("When We First Met");
        movie5.setDescription("Noah meets Avery at a Halloween party and falls in love but gets friend-zoned. 3 years later, she's engaged to someone else. Noah returns in a time machine to fix things.");
        movie5.setGenre(genre2);
        movie5.setRating(1);
        movieRepository.save(movie5);

        Movie movie6 = new Movie();
        movie6.setName("Good Boys");
        movie6.setDescription("Three 6th grade boys ditch school and embark on an epic journey while carrying accidentally stolen drugs, being hunted by teenage girls, and trying to make their way home in time for a long-awaited party.");
        movie6.setGenre(genre2);
        movie6.setRating(3);
        movieRepository.save(movie6);

        //genre3 movies
        Movie movie7 = new Movie();
        movie7.setName("No One Will Save You");
        movie7.setDescription("An exiled anxiety-ridden homebody must battle an alien who's found its way into her home.");
        movie7.setGenre(genre3);
        movie7.setRating(2);
        movieRepository.save(movie7);

        Movie movie8 = new Movie();
        movie8.setName("A Haunting in Venice");
        movie8.setDescription("In post-World War II Venice, Poirot, now retired and living in his own exile, reluctantly attends a seance. But when one of the guests is murdered, it is up to the former detective to once again to uncover the killer.");
        movie8.setGenre(genre3);
        movie8.setRating(1);
        movieRepository.save(movie8);

        Movie movie9 = new Movie();
        movie9.setName("Saw X");
        movie9.setDescription("A sick and desperate John travels to Mexico for a risky and experimental medical procedure in hopes of a miracle cure for his cancer only to discover the entire operation is a scam to defraud the most vulnerable.");
        movie9.setGenre(genre3);
        movie9.setRating(1);
        movieRepository.save(movie9);

        //genre4 movies
        Movie movie10 = new Movie();
        movie10.setName("The Terminator");
        movie10.setDescription("A human soldier is sent from 2029 to 1984 to stop an almost indestructible cyborg killing machine, sent from the same year, which has been programmed to execute a young woman whose unborn son is the key to humanity's future salvation.");
        movie10.setGenre(genre4);
        movie10.setRating(2);
        movieRepository.save(movie10);

        Movie movie11 = new Movie();
        movie11.setName("The Matrix");
        movie11.setDescription("When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.");
        movie11.setGenre(genre4);
        movie11.setRating(2);
        movieRepository.save(movie11);

        Movie movie12 = new Movie();
        movie12.setName("Interstellar");
        movie12.setDescription("When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.");
        movie12.setGenre(genre4);
        movie12.setRating(1);
        movieRepository.save(movie12);

        //genre5 movies
        Movie movie13 = new Movie();
        movie13.setName("Movie 13");
        movie13.setDescription("Movie 13 Description");
        movie13.setGenre(genre5);
        movie13.setRating(2);
        movieRepository.save(movie13);

        Movie movie14 = new Movie();
        movie14.setName("Movie 14");
        movie14.setDescription("Movie 14 Description");
        movie14.setGenre(genre5);
        movie14.setRating(2);
        movieRepository.save(movie14);

        Movie movie15 = new Movie();
        movie15.setName("Movie 15");
        movie15.setDescription("Movie 15 Description");
        movie15.setGenre(genre5);
        movie15.setRating(1);
        movieRepository.save(movie15);


    }

}
