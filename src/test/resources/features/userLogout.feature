@LogoutTests
Feature: Logout get request

Background: set authentication to logout user
Given Set bearer token in header

@logoutpositive
Scenario: Check admin able to logout  
Given User creates GET request for logout 
When User send GET HTTP request with valid endpoint
Then User recieves 200 created with Logout successful message

@logoutnegative
Scenario: Check admin able to logout  with invalid method
Given User creates POST request 
When User send POST HTTP request with endpoint
Then User recieves 405 method not allowed
@logoutpositive
Scenario: Check dietician able to logout  
Given User creates GET request for logout 
When User send GET HTTP request with endpoint
Then User recieves 200 created with Logout successful message

Scenario: Check dietician able to logout  with invalid method
Given User creates POST request 
When User send POST HTTP request with endpoint
Then User recieves 405 method not allowed

Scenario: Check patient able to logout  
Given User creates GET request for logout
When User send GET HTTP request with endpoint
Then User recieves 200 created with Logout successful message

Scenario: Check atient able to logout  with invalid method
Given User creates POST request 
When User send POST HTTP request with endpoint
Then User recieves 405 method not allowed