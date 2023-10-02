Feature: Movie Rest API functionalities

  Scenario: User able to add and remove a movie
    Given A list of movies are available
    When I add a movie to my movies list
    Then The movie is added
#    When I remove  a movie from my movies list
#    Then The movie is removed
#    When I edit a movie from my movies list
#    Then The movie is edited