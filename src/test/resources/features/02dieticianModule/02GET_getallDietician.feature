@DieticianAPI @GetAllDietician
Feature: Post Request for Dietician Module

  Background: Get all Dietician Details
    Given Set admin bearer token
    
  @GetAllDieticianTest_001
  Scenario: Check admin able to retrieve all dietician
    Given Admin create GET request 
    When Admin send GET http request with endpoint
    Then Admin recieves 200 ok with response body
  

 