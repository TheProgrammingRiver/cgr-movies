# CGR-movies
A Java-based web application designed for users to curate their movie consumption. The application provides a secure platform to access movie history privately.

### Approach

In this project, our goal was to create a movie database system with secure access. To achieve this, we began by defining our user stories
to guide the development process, such as enabling users to search for movies by genre and securing endpoints. To manage our tasks and collaborate
as a team, we used [Github Projects](https://github.com/users/GabrielleYnara/projects/3), adopting an Agile project management approach.


Collaborative development was at the core of our process, with frequent driver-navigator and occasional pair programming, to facilitate knowledge sharing
and ensure unified coding standards. We began by defining our models, established security
measures using Spring Security and JSON Web Tokens, adopted Behavior-Driven Development (BDD) testing with Cucumber and Rest Assured to validate endpoints, and finally
implemented the genre and movie endpoints.

### User-Stories
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
  <summary>Genre</summary>

1. As a logged-in user I want to add a genre, so I can categorize my movies.
   - The user must input a unique genre.
      - The application should show an error if not.
   - The user can input a description.

2. As a logged-in user I want to see all the genres.
   - The genre list should be populated.
   - The application should provide the genre's list.
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

### ERD
![ERD](https://github.com/GabrielleYnara/cgr-movies/blob/security/assets/cgr-movies.png)

### API Endpoints
| HTTP Methods | Endpoint URL                           | Functionality                             | Access    | 
|--------------|----------------------------------------|-------------------------------------------|-----------|
| POST         | `/api/users/register/`                 | Register a new user                       | public    |
| POST         | `/api/users/login/`                    | Login a registered user                   | public    |
| POST         | `/api/genres/`                         | Create a genre                            | private   |
| GET          | `/api/genres/`                         | Get all the genres                        | private   |
| POST         | `/api/genres/1/movies`                 | Create a movie by a genre ID              | private   |
| GET          | `/api/genres/1/movies/`                | Get all the movies of a given genre       | private   |
| GET          | `/api/genres/1/movies/1/`              | Get a movie by the ID and genre ID        | private   |
| PUT          | `/api/genres/1/movies/1/`              | Edit a movie by the ID and genre ID       | private   |
| DELETE       | `/api/genres/1/movies/1/`              | Delete a movie by the ID and genre ID     | private   |
| GET          | `/api/movies/`                         | Get all the movies                        | private   |
| GET          | `/api/movies/byStatus/?status=WATCHED` | Get all the movies filtered by status     | private   |

### Tools and Technologies
- Java
- Spring Boot
- Spring Security
- JSON Web Tokens
- Cucumber
- Rest Assured
- H2 Database
- Maven
- Git Version Control
- Git Projects
- Postman

### Future Improvements
- Implement a user interface.
- Add a user profile.
- Change relationship to many-to-many between genre and movies.

### Acknowledgments
This project was created as a part of the Software Engineering Immersive program offered by General Assembly.  
It served as a collaborative group project to practice our skills in Java, Object-Oriented Programming, Spring Boot, 
Cucumber, and GitHub branch collaboration. 
#### Team Members
Courtney Moreland: [LinkedIn]( https://www.linkedin.com/in/courtney-moreland/), [GitHub](https://github.com/courtneymcodes)  
Gabrielle Ynara: [LinkedIn](https://www.linkedin.com/in/gabrielleynara/), [GitHub](https://github.com/GabrielleYnara)  
River Dejesus: [LinkedIn](https://www.linkedin.com/in/river-dejesus/), [GitHub](https://github.com/TheProgrammingRiver)  


