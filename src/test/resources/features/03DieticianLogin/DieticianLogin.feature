Feature: dietician login 

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