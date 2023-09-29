Feature: Genre Rest API functionalities

  Scenario: User able to add and get all the genres
    Given A list of genres are available
    When I view the list of genres
    Then I should see the genre list
    When I add a genre
    Then The genre is added