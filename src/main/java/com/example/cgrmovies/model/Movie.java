package com.example.cgrmovies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    public enum MovieStatus {
        WATCHED, ON_HOLD, DROPPED, WATCHING, WISHLIST
    }//TODO-------------------------------------------

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "movie_id")
    private Genre genre;

    @Column
    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @Column
    private int rating;


    public Movie() {
    }

    public Movie(Long id, String name, String description, Genre genre, int rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
    }

    public Movie(String name, String description, Genre genre, int rating) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", status=" + status +
                ", rating=" + rating +
                '}';
    }
}
