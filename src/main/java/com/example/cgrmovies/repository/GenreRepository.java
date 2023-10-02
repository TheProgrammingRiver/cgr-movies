package com.example.cgrmovies.repository;

import com.example.cgrmovies.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByUserId(Long userId);

    Optional<Genre> findByNameAndUserId(String name, Long userId);

    List<Genre> findAllByUserId(Long userId);
}
