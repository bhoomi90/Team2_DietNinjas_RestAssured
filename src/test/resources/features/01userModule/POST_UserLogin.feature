@DieticianAPI @UserLogin
Feature: Post Request for User Login

  @UserLoginTest_001
  Scenario: Check user able to login as admin with valid data
    Given User creates Post request with request body. Request body : Userlogin and password
    When User send POST HTTP request with endpoint
    Then User recieves 200 created with response body
