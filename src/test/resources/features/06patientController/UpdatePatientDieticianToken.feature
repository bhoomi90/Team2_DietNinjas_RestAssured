@UpdatePatientWithDietician
Feature: Put Operation [update patient]

Background: Dietician bearer token was set, and the Dietician successfully created a patient account

@UpdatePatientWithDietician_001
Scenario: Check dietician able to update patient with valid data, patient id and token 
Given Dietician creates PUT request by entering valid data. ( Mandatory and additional details) into the form-data key and value fields.
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 200 ok and with updated response body after update patient request. 

@UpdatePatientWithDietician_002
Scenario: Check dietician able to update patient only with valid mandatory details
Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields.
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 200 ok and with updated response body after update patient request. 

@UpdatePatientWithDietician_003
Scenario: Check dietician able to update patient with mandatory fields empty and only with valid additional details
Given Dietician creates PUT request by entering only valid additional details into the form-data key and value fields.
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 400 Bad request after update patient request

@UpdatePatientWithDietician_004
Scenario: Check dietician able to update patient with invalid data
Given Dietician creates PUT request by entering only invalid additional details into the form-data key and value fields.
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 400 Bad request after update patient request

@UpdatePatientWithDietician_005
Scenario: Check dietician able to update patient with valid data and invalid patient id in path parameter
Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields.
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 404 not found after update patient request

@UpdatePatientWithDietician_006
Scenario: Check dietician able to update patient with existing file by not attaching new file 
Given Dietician creates PUT request by not attaching any file into the form-data key and value fields.
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 200 ok and with updated response body after update patient request. 

@UpdatePatientWithDietician_007
Scenario: Check dietician able to update patient with valid data and invalid method
Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
When Dietician send POST http request with endpoint to update patient
Then Dietician recieves 405 method not allowed after update patient request

@UpdatePatientWithDietician_008
Scenario: Check dietician able to update patient with valid data and invalid endpoint
Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
When Dietician sent PUT http request with invalid endpoint to update patient
Then Dietician recieves 404 not found after update patient request

@UpdatePatientWithDietician_009
Scenario: Check dietician able to update patient with valid data, patient id and invalid content type
Given Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 415 unsupported media type after update patient request
