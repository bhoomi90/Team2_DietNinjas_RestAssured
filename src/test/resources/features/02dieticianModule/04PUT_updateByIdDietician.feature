@DieticianAPI @UpdateByIdDietician
Feature: PUT Request for Dietician Module

  Background: Update Dietician Details
    Given Set admin bearer token in Dietician Module
    Given Admin creates POST request with valid data in Dietician Module

  @UpdateByIdDieticianTest_001
  Scenario: Check admin able to update dietician with valid data , dietician id and token
    Given Admin creates PUT request with valid data. ( Mandatory and additional details)
    When Admin send PUT http request with endpoint in update dietician module
    Then Admin recieves 200 ok and with updated response body.

  @UpdateByIdDieticianTest_002
  Scenario: Check admin able to update dietician only with valid mandatory details and dietician id
    Given Admin creates PUT request only with valid mandatory details
    When Admin send PUT http request with endpoint in update dietician module
    Then Admin recieves 200 ok and with updated response body.

  @UpdateByIdDieticianTest_003
  Scenario: Check admin able to update dietician only with valid additional details
    Given Admin creates PUT request only with valid additional details
    When Admin send PUT http request with endpoint in update dietician module
    Then Admin recieves 400 Bad request in update Module

  @UpdateByIdDieticianTest_004
  Scenario: Check admin able to update dietician with invalid data and dietician id
    Given Admin creates PUT request only with invalid additional details
    When Admin send PUT http request with endpoint in update dietician module
    Then Admin recieves 400 Bad request in update Module

  @UpdateByIdDieticianTest_005
  Scenario: Check admin able to update dietician with valid data and invalid dietician id
    Given Admin creates PUT request only with valid mandatory details updateid
    When Admin send PUT http request with endpoint in update invalidid dietician module
    Then Admin recieves 404 not found in update Dietician Module

  @UpdateByIdDieticianTest_006
  Scenario: Check admin able to update dietician with valid data, dietician id and invalid method
    Given Admin creates POST request only with valid details in updateDietician Module
    When Admin send POST http request with endpoint in updateDietician Module
    Then Admin recieves 405 method not allowed in updateDietician Module
    
  @UpdateByIdDieticianTest_007
  Scenario: Check admin able to update dietician with valid data, dietician id and invalid endpoint
    Given Admin creates PUT request with valid data in update dietician module
    When Admin sent PUT http request with invalid endpoint
    Then Admin recieves 404 not found in update Dietician Module 
       
  @UpdateByIdDieticianTest_008
  Scenario: Check admin able to update dietician with valid data, dietician id and invalid content type
    Given Admin creates PUT request with valid data and invalid content type
    When Admin send PUT http request with endpoint in update dietician module
    Then Admin recieves 415 unsupported media type in update Dietician Module
