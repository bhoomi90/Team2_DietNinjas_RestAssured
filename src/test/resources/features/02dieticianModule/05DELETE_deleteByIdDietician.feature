@DieticianAPI @UDeleteByIdDietician
Feature: Delete Request for Dietician Module

  Background: Delete Dietician Details By Id
    Given Set admin bearer token in Dietician Module
    Given Admin creates POST request with valid data in Dietician Module

  @DeleteByIdDieticianTest_001
  Scenario: Check admin able to delete dietician by ID
    Given Admin create DELETE request in dietician Module
    When Admin send DELETE http request with endpoint in dietician Module
    Then Admin recieves 200 ok with details of the dietician id Delete dietician Module

  @DeleteByIdDieticianTest_002
  Scenario: Check admin able to delete dietician by id with invalid method
    Given Admin create POST request in dietician Module
    When Admin send POST http request with endpoint in dietician Module
    Then Admin recieves 405 method not allowed id Delete dietician Module

  @DeleteByIdDieticianTest_003
  Scenario: Check admin able to delete dietician by invalid id
    Given Admin create DELETE request in dietician Module by invalid id
    When Admin send DELETE http request with endpoint in dietician Module by invalid id
    Then Admin recieves 404 not found id Delete dietician Module

  @DeleteByIdDieticianTest_004
  Scenario: Check admin able to delete dietician by id with invalid endpoint
    Given Admin create DELETE request in dietician Module with invalid endpoint
    When Admin send DELETE http request with invalid endpoint in dietician Module
    Then Admin recieves 404 not found id Delete dietician Module
