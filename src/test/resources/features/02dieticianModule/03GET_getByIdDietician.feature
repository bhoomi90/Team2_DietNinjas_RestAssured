@DieticianAPI @GetByIdDietician
Feature: GET Request for Dietician Module

  Background: Get By Id Dietician Details
    Given Set admin bearer token
    
  @GetByIdDieticianTest_001
  Scenario: Check admin able to retrieve dietician by ID
    Given Admin create GET request for dietician by ID
    When Admin send GET http request with endpoint and dietician ID
    Then Admin recieves 200 ok with details of the dietician id
  
 @GetByIdDieticianTest_002
  Scenario: Check admin able to retrieve dietician by id with invalid method
    Given Admin create POST request get by Id module
    When Admin send POST http request with endpoint get by Id module
    Then Admin recieves 405 method not allowed in get by Id module
    
  @GetByIdDieticianTest_003
  Scenario: Check admin able to retrieve dietician by invalid id
    Given Admin create GET request get by Id module
    When Admin send GET http request with endpoint get by Id module
    Then Admin recieves 404 not found in get by Id module  
    
  @GetByIdDieticianTest_004
  Scenario: Check admin able to retrieve dietician by id with invalid endpoint
    Given Admin create GET request get by Id dietician module 
    When Admin send GET http request with endpoint get by Id dietician module
    Then Admin recieves 404 not found in get by Id module    
