Feature: Post Operation [create patient]

Background: Set dietician bearer token

Scenario Outline: Check dietician able to create patient with valid data and token 
Given Dietician creates POST request by entering valid data with "<scenarioName>". ( Mandatory and additional details) into the form-data key and value fields.
When Dietician send POST http request with endpoint to create patient with "<scenarioName>"
Then Dietician recieves 201 created and with response body. (Auto created dietician ID and login password)

	Examples:
		| scenarioName    |
		| Create patient  |

#Scenario: Check dietician able to create patient only with valid mandatory details
#Given Dietician creates POST request only by valid mandatory details into the form-data key and value fields.
#When Dietician send POST http request with endpoint to create patient
#Then Dietician recieves 201 created and with response body. (Auto created dietician ID and login password)
#
#Scenario: Check dietician able to create patient only with valid additional details
#Given Dietician creates POST request only by valid additional details into the form-data key and value fields.
#When Dietician send POST http request with endpoint to create patient
#Then Dietician recieves 400 Bad request after create patient request
#
#Scenario: Check dietician able to create patient with invalid data (mandatory details)
#Given Dietician creates POST request only by invalid mandatory details into the form-data key and value fields.
#When Dietician send POST http request with endpoint to create patient
#Then Dietician recieves 400 Bad request after create patient request
#
#Scenario: Check dietician able to create patient with valid mandatory fields and invalid data (additional details)
#Given Dietician creates POST request only by invalid additional details into the form-data key and value fields.
#When Dietician send POST http request with endpoint to create patient
#Then Dietician recieves 400 Bad request after create patient request
#
#Scenario: Check dietician able to create patient with valid data and invalid method
#Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
#When Dietician send PUT http request with endpoint to create patient
#Then Dietician recieves 405 method not allowed after create patient request
#
#Scenario: Check dietician able to create patient with valid data and invalid endpoint
#Given Dietician creates POST request by entering valid data into the form-data key and value fields.
#When Dietician send POST http request with invalid endpoint to create patient
#Then Dietician recieves 404 not found after create patient request
#
#Scenario: Check dietician able to create patient with valid data and invalid content type
#Given Dietician creates POST request by entering valid data into the form-data key and value fields and invalid content type
#When Dietician send POST http request with endpoint to create patient
#Then Dietician recieves 415 unsupported media type after create patient request

