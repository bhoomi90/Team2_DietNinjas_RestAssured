Feature: Put Operation [Add New Reports with/without Vitals for existing Patient]

Background: Set no auth

Scenario: Check dietician able to add new reports for existing patient with valid data
Given Dietician creates PUT request by entering valid data into the form-data key and value fields and valid patient ID
When Dietician send PUT http request with endpoint
Then Dietician recieves 401 unauthorized
