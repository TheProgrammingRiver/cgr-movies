package com.example.cgrmovies.service;

import com.example.cgrmovies.exception.InformationExistsException;
import com.example.cgrmovies.model.Genre;
import com.example.cgrmovies.model.User;
import com.example.cgrmovies.repository.GenreRepository;
import com.example.cgrmovies.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Genre> genreOptional = genreRepository.findByNameAndUserId(genre.getName(), getCurrentLoggedInUser().getId());
        if(genreOptional.isEmpty()){
            genre.setUser(getCurrentLoggedInUser());
            return genreRepository.save(genre);
        } else {
            throw new InformationExistsException("Genre " + genre.getName() + " already exists");
        }

    }

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }




}
