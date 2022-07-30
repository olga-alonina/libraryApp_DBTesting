@wip
Feature: Dashboard Page


  Scenario: Dashboard data test_01
    Given the user logged in as "librarian"
    When user gets all information from modules
    Then the informations should be same with database