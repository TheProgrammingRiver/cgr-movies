Feature: Rest API functionalities

  Scenario: Manage User access via API
    When I add a new user
    Then A user is registered
    When A registered user logs in
    Then A user is authenticated
