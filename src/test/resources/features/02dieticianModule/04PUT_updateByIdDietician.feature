@DieticianAPI @UpdateByIdDietician
Feature: PUT Request for Dietician Module

  Background: Update Dietician Details
    Given Set admin bearer token
    Given Admin creates POST request with valid data

    
  @UpdateByIdDieticianTest_001
  Scenario: Check admin able to update dietician with valid data , dietician id and token 
    Given Admin creates PUT request with valid data. ( Mandatory and additional details)
    When Admin send PUT http request with endpoint
    Then Admin recieves 200 ok and with updated response body. 
    
  
  