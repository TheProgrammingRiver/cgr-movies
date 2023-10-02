# CGR-movies
A Java-based web application designed for users to curate their movie consumption. The application provides a secure platform to access movie history privately.

## USER-Stories
<details>
  <summary>User</summary>

1. As a new user I want to be able to register so that I can have access to the application.
    - The user must input an email and password.
    - The email must be unique.
        - The application should show an error if an email is not unique.

2. As a user I want to log in account so that I can access my personalized content.
- The user must provide a valid email and password combination.
- The application should grant access to the personalized application content.
- The application should show an error if given an invalid email and password.

</details>
<details>
  <summary>Movie</summary>

1. As a logged-in user I want to add a movie to my movies list so that I can keep track of my movie preferences.
    - The user must input a unique movie name.
        - The application should show an error if the name is duplicated.
    - The user must input an existing genre.
        - The application should show an error if the genre is not found.
    - The user can input description, status, and rating.
        - If no status is provided, the application defaults the status to "wishlist".
    - The application should add the movie to the user's list.
    - The application should show a success message.

2. As a logged-in user I want to edit a movie's information so I can an updated list.
    - The user must input a valid movie name.
        - The application should show an error if not.
    - The user must input an existing genre.
        - The application should show an error if the genre is not found.
    - The application should update the movie retrieved.
    - The application should show a success message.

3. As a logged-in user I want to delete a movie from my list.
    - The user must input a valid movie name.
        - The application should show an error if not.
    - The user must input an existing genre.
        - The application should show an error if the genre is not found.
    - The application should delete the given movie.
    - The application should show a success message.

4. As a logged-in user I want to search for a specific movie.
    - The user must input a valid movie name.
        - The application should show an error if not.
    - The user must input an existing genre.
        - The application should show an error if the genre is not found.
    - The application should retrieve the given movie.

5. As a logged-in user I want to see all the movies in my list.
    - The movie list should be populated.
    - The application should provide the movie's list.

6. As a logged-in user I want to be able to list the movies by status.
    - The user must input a valid status.
        - The application should show an error if not.
    - The movie list should be populated.
    - The application should provide the movie's list.

7. As a logged-in user I want to be able to list the movies by genre.
    - The user must input a valid genre.
        - The application should show an error if not.
    - The user must input an existing genre.
        - The application should show an error if the genre is not found.
    - The movie list should be populated.
    - The application should provide the movie's list.
</details>

<details>
  <summary>Genre</summary>

1. As a logged-in user I want to add a genre, so I can categorize my movies.
    - The user must input a unique genre.
        - The application should show an error if not.
    - The user can input a description.

2. As a logged-in user I want to see all the genres.
    - The genre list should be populated.
    - The application should provide the genre's list.
</details>

### ERD
![ERD](https://github.com/GabrielleYnara/cgr-movies/blob/security/assets/cgr-movies.png)

## API Endpoints
| HTTP Methods | Endpoint URL                          | Functionality                             | Access    | 
|--------------|---------------------------------------|-------------------------------------------|-----------|
| POST         | `/api/users/register/`                | Register a new user                       | public    |
| POST         | `/api/users/login/`                   | Login a registered user                   | public    |
| POST         | `/api/genres/`                        | Create a genre                            | private   |
| GET          | `/api/genres/`                        | Get all the genres                        | private   |
| POST         | `/api/genres/1/movies`                | Create a movie by a genre ID              | private   |
| GET          | `/api/genres/1/movies/`               | Get all the movies of a given genre       | private   |
| GET          | `/api/genres/1/movies/1/`             | Get a movie by the ID and genre ID        | private   |
| PUT          | `/api/genres/1/movies/1/`             | Edit a movie by the ID and genre ID       | private   |
| DELETE       | `/api/genres/1/movies/1/`             | Delete a movie by the ID and genre ID     | private   |
| GET          | `/api/movies/`                        | Get all the movies                        | private   |
| GET          | `/api/genre/1/movies/status?watched`  | Get all the movies filtered by status     | private   |

