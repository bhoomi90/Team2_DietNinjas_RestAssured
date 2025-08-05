@DieticianAPI @GetAllDietician
Feature: GET Request for Dietician Module

  Background: Get all Dietician Details
    Given Set admin bearer token in Dietician Module
    
  @GetAllDieticianTest_001
  Scenario: Check admin able to retrieve all dietician
    Given Admin create GET request 
    When Admin send GET http request with endpoint
    Then Admin recieves 200 ok with response body
    
  @GetAllDieticianTest_002
  Scenario: Check admin able to retrieve all dietician with invalid method
    Given Admin create PUT request  
    When Admin send PUT http request with endpoint in ALL Dietician 
    Then Admin recieves 405 method not allowed in in ALL Dietician 
  
  @GetAllDieticianTest_003
  Scenario: Check admin able to retrieve all dietician with invalid endpoint
    Given Admin create GET request in dietician all module 
    When Admin send GET http request with invalid endpoint 
    Then Admin recieves 404 not found in all Dietician
 