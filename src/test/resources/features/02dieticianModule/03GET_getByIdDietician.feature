@DieticianAPI @GetByIdDietician
Feature: GET Request for Dietician Module

  Background: Get By Id Dietician Details
    Given Set admin bearer token
    
  @GetByIdDieticianTest_001
  Scenario: Check admin able to retrieve dietician by ID
    Given Admin create GET request for dietician by ID
    When Admin send GET http request with endpoint and dietician ID
    Then Admin recieves 200 ok with details of the dietician id
  

