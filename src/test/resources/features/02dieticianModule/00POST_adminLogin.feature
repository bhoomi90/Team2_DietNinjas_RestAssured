@DieticianAPI @AdminLogin
Feature: Post Request for User Login

  @AdminLoginTest_001
  Scenario: Check user able to login as admin with valid data for admin login feature
    Given User creates Post request with request body. Request body : Userlogin and password for admin login feature
    When User send POST HTTP request with endpoint for admin login feature
    Then User recieves 200 created with response body for admin login feature
