package model;

public class Movie {

    private Long id;

    private String name;

    private String description;

    private Genre genre;

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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", rating=" + rating +
                '}';
    }
}
