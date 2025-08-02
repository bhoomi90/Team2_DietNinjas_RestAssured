Feature: Post Operation [create patient]

Background: Set patient bearer token

Scenario Outline: Check patient able to create patient with valid data and patient token
Given Patient creates POST request by entering valid data into the form-data key and value fields with "<scenarioName>"
When Patient send POST http request with endpoint to create patient with "<scenarioName>"
Then Patient recieves 403 forbidden after create patient request

		Examples:
		| scenarioName                              |
		| Create patient with invalid token         |
	