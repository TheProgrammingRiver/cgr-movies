Feature: Movie Rest API functionalities

  Scenario: User able to add and remove a movie
    Given A list of movies with a specific genre are available
    When I add a movie with a specific genre to my list
    Then The movie is added
    When I edit a movie from my movies list
    Then The movie is edited
    When I remove a movie with a specific genre from my list
    Then The movie is removed
    When I see all the movies
    Then All movies are shown