Feature: User login post request

Background: No Authorization

@test
Scenario: Check user able to login as admin with valid data
Given User creates Post request with request body.Request body : Userlogin and password
When User send POST HTTP request with endpoint
Then User recieves 200 created with response body

@test1
Scenario: Check user able to login as admin with invalid credential
Given User creates Post request with invalid credential
When User send POST HTTP request with endpoint
Then User recieves 401 unauthorized

@test2
Scenario: Check user able to login as admin with valid credential and invalid method
Given User creates GET request with request body.Request body : Userlogin and password
When User send GET HTTP request with endpoint
Then User recieves 405 method not allowed

@test3
Scenario: Check user able to login as admin with valid credential and invalid endpoint
Given User creates Post request with request body.Request body : Userlogin and password
When User send POST HTTP request with invalid endpoint
Then User recieves 401 unauthorized

@test4
Scenario: Check user able to login as admin with valid credential and invalid content type
Given User creates Post request with request body and invalid content type.Request body : Userlogin and password
When User send POST HTTP request with endpoint
Then User recieves 415 unsupported media type

@patientvalidlogin
Scenario: Check user able to login as patient with valid credential
Given User creates Post request with patient input request body.Request body : Userlogin and password
When User send POST HTTP request with endpoint
Then User recieves 200 created with response body

@patientinvalidlogin
Scenario: Check user able to login as patient with invalid credential
Given User creates Post request with invalid patient credential
When User send POST HTTP request with endpoint
Then User recieves 401 unauthorized

###############  Logout No Auth Scenarios ############################
@logout1
Scenario: Check admin able to logout  
Given User creates GET request 
When User send GET HTTP request with endpoint
Then User recieves 401 unauthorized

Scenario: Check dietician able to logout  
Given User creates GET request 
When User send GET HTTP request with endpoint
Then User recieves 401 unauthorized

Scenario: Check patient able to logout  
Given User creates GET request 
When User send GET HTTP request with endpoint
Then User recieves 401 unauthorized
