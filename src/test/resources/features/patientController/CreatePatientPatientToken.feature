Feature: Post Operation [create patient]

Background: Set patient bearer token

Scenario Outline: Check patient able to create patient with valid data and patient token
Given Patient creates POST request by entering valid data into the form-data key and value fields with "<testCaseID>"
When Patient send POST http request with endpoint to create patient with "<testCaseID>"
Then Patient recieves 403 forbidden after create patient request

		Examples:
		| testCaseID |
		| p2         |
	