@DieticianAPI @CreateDietician
Feature: Post Request for Dietician Module

  Background: Create Dietician before set the admin bearer token
    Given Set admin bearer token

  @CreateDieticianTest_001
  Scenario: Check admin able to create dietician with valid data and token
    Given Admin creates POST request with valid data
    When Admin send POST http request with endpoint
    Then Admin recieves 201 created and with response body