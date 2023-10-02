package com.example.cgrmovies.service;

import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.model.User;
import com.example.cgrmovies.repository.GenreRepository;
import com.example.cgrmovies.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private GenreRepository genreRepository;
    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public Genre createGenre(Genre genre){
        return genreRepository.save(genre);
    }

    //TODO get the current user

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }




}
