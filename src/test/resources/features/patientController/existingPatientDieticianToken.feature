Feature: Put Operation [Add New Reports with/without Vitals for existing Patient]

Background: Dietician bearer token was set, and the Dietician successfully created a patient account

Scenario: Check dietician able to create patient with valid data and token 
Given Dietician creates POST request by entering valid data. ( Mandatory and additional details) into the form-data key and value fields.
When Dietician send POST http request with endpoint
Then Dietician recieves 201 created and with response body. (Auto created dietician ID and login password)

Scenario: Check dietician able to create patient only with valid mandatory details
Given Dietician creates POST request only by valid mandatory details into the form-data key and value fields.
When Dietician send POST http request with endpoint
Then Dietician recieves 201 created and with response body. (Auto created dietician ID and login password)

Scenario: Check dietician able to create patient only with valid additional details 
Given Dietician creates POST request only by valid additional details into the form-data key and value fields.
When Dietician send POST http request with endpoint
Then Dietician recieves 400 Bad request

Scenario: Check dietician able to create patient with invalid data (mandatory details)
Given Dietician creates POST request only by invalid mandatory details into the form-data key and value fields.
When Dietician send POST http request with endpoint
Then Dietician recieves 400 Bad requestDietician recieves 201 created and with response body. (Auto created dietician ID and login password)

Scenario: Check dietician able to create patient with valid mandatory fields and invalid data (additional details)
Given Dietician creates POST request only by invalid additional details into the form-data key and value fields.
When Dietician send POST http request with endpoint
Then Dietician recieves 400 Bad request

Scenario: Check dietician able to create patient with valid data and invalid method
Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
When Dietician send PUT http request with endpoint
Then Dietician recieves 405 method not allowed

Scenario: Check dietician able to create patient with valid data and invalid endpoint
Given Dietician creates POST request by entering valid data into the form-data key and value fields.
When Dietician sent POST http request with invalid endpoint
Then Dietician recieves 404 not found

Scenario: Check dietician able to create patient with valid data and invalid content type
Given Dietician creates POST request by entering valid data into the form-data key and value fields and invalid content type
When Dietician send POST http request with endpoint
Then Dietician recieves 415 unsupported media type