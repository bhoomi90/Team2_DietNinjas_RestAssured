@DieticianAPI @UDeleteByIdDietician
Feature: Delete Request for Dietician Module

  Background: Delete Dietician Details By Id
    Given Set admin bearer token
    Given Admin creates POST request with valid data

    
  @DeleteByIdDieticianTest_001
  Scenario: Check admin able to delete dietician by ID 
    Given Admin create DELETE request 
    When Admin send DELETE http request with endpoint
    Then Admin recieves 200 ok with details of the dietician id Delete
  