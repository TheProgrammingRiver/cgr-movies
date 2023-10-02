package com.example.cgrmovies.service;

import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository genreRepository;
    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre createGenre(Genre genre){
        return genreRepository.save(genre);
    }

}
