package com.example.cgrmovies.repository;

import com.example.cgrmovies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByNameAndGenreId(String name, Long genreId);
    List<Movie> findAllByGenreId(Long genreId);
    Optional<Movie> findByIdAndGenreId(Long movieId, Long genreId);
    List<Movie> findAllByGenreIdIn(List<Long> genreIds);
}
