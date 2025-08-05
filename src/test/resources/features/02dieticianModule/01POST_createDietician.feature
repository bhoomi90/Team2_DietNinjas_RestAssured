@DieticianAPI @CreateDietician
Feature: Post Request for Dietician Module

  Background: Create Dietician before set the admin bearer token
    Given Set admin bearer token in Dietician Module

  @CreateDieticianTest_001
  Scenario: Check admin able to create dietician with valid data and token
    Given Admin creates POST request with valid data in Dietician Module
    When Admin send POST http request with endpoint Dietician Module
    Then Admin recieves 201 created and with response body Dietician Module

  @CreateDieticianTest_002
  Scenario: Check admin able to create dietician with invalid data
    Given Admin creates POST request only with invalid additional details
    When Admin send POST http request with endpoint Dietician Module
    Then Admin recieves 400 Bad request in CreateDietician

  @CreateDieticianTest_003
  Scenario: Check admin able to create dietician with valid data and invalid method
    Given Admin creates PUT request only with valid details
    When Admin send PUT http request with endpoint in create dietician module
    Then Admin recieves 405 method not allowed in CreateDietician

  @CreateDieticianTest_004
  Scenario: Check admin able to create dietician with valid data and invalid endpoint
    Given Admin creates POST request with valid data in dieticianModule
    When Admin sent POST http request with invalid endpoint
    Then Admin recieves 404 not found in CreateDietician

  @CreateDieticianTest_005
  Scenario: Check admin able to create dietician with valid data and invalid content type
    Given Admin creates POST request with valid data and invalid content type
    When Admin send POST http request with endpoint Dietician Module
    Then Admin recieves 415 unsupported media type in CreateDietician
 
  @CreateDieticianTest_006
  Scenario: Check admin able to create dietician only with valid additional details
    Given Admin creates POST request only with valid additional details
    When Admin send POST http request with endpoint Dietician Module
    Then Admin recieves 400 Bad request in CreateDietician