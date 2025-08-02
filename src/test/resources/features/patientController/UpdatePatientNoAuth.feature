Feature: Put Operation [update patient]

Background: Set no auth

Scenario: Check dietician able to update patient with valid data
Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 401 unauthorized after update patient request
