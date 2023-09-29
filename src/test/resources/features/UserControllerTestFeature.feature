Feature: Rest API functionalities

  Scenario: Manage User access via API
    Given A user
    When I register a user
    Then A user is added
    When A user logs in
    Then a user is authenticated
