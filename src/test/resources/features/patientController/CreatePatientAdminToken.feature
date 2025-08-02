Feature: Post Operation [create patient]

Background: Set admin bearer token

Scenario Outline: Check admin able to create patient with valid data and admin token
Given Admin creates POST request by entering valid data into the form-data key and value fields with "<scenarioName>"
When Admin send POST http request with endpoint to create patient with "<scenarioName>"
Then Admin recieves 403 forbidden after create patient request

			Examples:
		| scenarioName                              |
		| Create patient with invalid token         |
