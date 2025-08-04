Feature: dietician login 

 @CreateDieticianForLogin_001
  Scenario: Check admin able to create dietician for dietician login with valid data and token
    Given Set admin bearer token
    Given Admin creates POST request with valid dietician data
    When Admin send POST http request with dietician endpoint
    Then Admin recieves 201 created and with dietician response body


@dieticianvalidlogin
Scenario: Check user able to login as dietician with valid credential
Given User creates Post request with dietician input request body.Request body : Userlogin and password
When User send POST HTTP request with endpoint
Then User recieves 200 created with response body

@dieticianinvalidlogin
Scenario: Check user able to login as dietician with invalid credential
Given User creates Post request with invalid dietician credential
When User send POST HTTP request with endpoint
Then User recieves 401 unauthorized