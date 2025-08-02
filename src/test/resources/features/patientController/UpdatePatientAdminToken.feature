Feature: Put Operation [update patient]

Background: Set admin bearer token

Scenario: Check admin able to update patient with valid data and admin token
Given Admin creates PUT request by entering valid data into the form-data key and value fields.
When Admin send PUT http request with endpoint to update patient
Then Admin recieves 403 forbidden after update patient request
