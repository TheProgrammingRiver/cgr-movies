package com.example.cgrmovies.service;

import com.example.cgrmovies.exception.InformationExistsException;
import com.example.cgrmovies.exception.InformationNotFoundException;
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

    /**
     * Sets the genre repository.
     */
    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Retrieves the current logged-in user.
     * @return  the current logged-in user
     */
    public User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Creates a new genre.
     *
     * @param  genre  the genre object to be created
     * @return        the created genre object
     */
    public Genre createGenre(Genre genre){
        Optional<Genre> genreOptional = genreRepository.findByNameAndUserId(genre.getName(), getCurrentLoggedInUser().getId());
        if(genreOptional.isEmpty()){
            genre.setUser(getCurrentLoggedInUser());
            return genreRepository.save(genre);
        } else {
            throw new InformationExistsException("Genre " + genre.getName() + " already exists");
        }

    }

    /**
     * Retrieves all genres associated with the current logged in user.
     * @return         	A list of genres.
     */
    public List<Genre> getAllGenres(){
        List<Genre> genreList = genreRepository.findAllByUserId(getCurrentLoggedInUser().getId());
        if(genreList.isEmpty()){
            throw new InformationNotFoundException("Genre list is empty");

        } else {
            return genreList;
        }
    }

    /**
     * Retrieves a genre by its ID.
     *
     * @param  genreId  the ID of the genre
     * @return          the genre with the specified ID
     */
    public Genre getGenreById(Long genreId){
        Optional<Genre> genre = genreRepository.findByIdAndUserId(genreId, getCurrentLoggedInUser().getId());
        if ( genre.isEmpty()){
            throw new InformationNotFoundException("Genre with id " + genreId + " not found");
        } else {
            return genre.get();
        }
    }
}
